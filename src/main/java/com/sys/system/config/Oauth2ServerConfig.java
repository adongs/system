package com.sys.system.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * oauth2服务
 *
 * @author yudong
 */
@Configuration
public class Oauth2ServerConfig {

    /**
     * 资源id
     */
    private static final String RESOURCE_ID_ORDER = "order";

    /**
     * 资源服务
     *
     * @author yudong
     */
    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        /**
         * 资源安全配置
         * resourceId:资源ID(可选的，建议配置，如果不为空，授权服务器会对它进行验证)
         *
         * @param resources
         */
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(Oauth2ServerConfig.RESOURCE_ID_ORDER);
        }

        /**
         * HTTP安全配置
         * authenticated() : 任何得到授权的用户都允许访问
         * 这里我采用的是注解授权,所以这里都允许
         *
         * @param http
         * @throws Exception
         */
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/**")
                    .authenticated();
        }
    }

    /**
     * 授权配置
     *
     * @author yudong
     */
    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        AuthenticationManager authenticationManager;
        @Autowired
        DataSource dataSource;


        /**
         * 配置客户端详细信息
         * 客户端信息: 请求这台服务终端,需要携带的参数
         * jdbc():数据库配置配置方式加载
         * 当然你也可以使用内存模式加载,内存模式后续会写在注释中,生产环境中建议使用数据库模式,方便修改
         *
         * @param clients
         * @throws Exception
         */
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.jdbc(dataSource);
        }

        /**
         * 配置授权服务,令牌(token)的访问端点和令牌服务
         * 当你请求令牌时,Spring Security 返回给你的可能不是想要的数据格式
         * 例如你想在返回的数据中加一个code使他成为你想要的格式
         * 你可以像如下这么做
         *
         * @param endpoints
         */
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
            endpoints.tokenStore(new JdbcTokenStore(dataSource) {
                /**
                 * 请求授权成功触发的方法
                 * @param token
                 * @param authentication
                 */
                @Override
                public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
                    //在这里你可以自定义你想要的数据格式
                    Map<String, Object> map = new HashMap<>();
                    map.putAll(token.getAdditionalInformation());
                    map.put("code", 0);
                    ((DefaultOAuth2AccessToken) token).setAdditionalInformation(map);
                    super.storeAccessToken(token, authentication);
                }
            })
                    .authenticationManager(authenticationManager)
                    .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                    .exceptionTranslator(new DefaultWebResponseExceptionTranslator() {
                        /**
                         * 请求授权失败触发的方法
                         * @param e
                         * @return
                         * @throws Exception
                         */
                        @Override
                        public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
                            e.printStackTrace();
                            //如果出现异常会触发这里(例如密码不正确)
                            // Carry on handling the exception
                            ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
                            HttpHeaders headers = new HttpHeaders();
                            headers.setAll(responseEntity.getHeaders().toSingleValueMap());
                            OAuth2Exception excBody = responseEntity.getBody();
                            //在这里你可以自定义你想要的数据格式
                            excBody.addAdditionalInformation("code", "1");
                            return new ResponseEntity<>(excBody, headers, responseEntity.getStatusCode());
                        }
                    });
        }

        /**
         * 来配置令牌端点(Token Endpoint)的安全约束.
         * allowFormAuthenticationForClients() : 允许表单验证
         * tokenKeyAccess("permitAll()"): 公开/oauth/token的接口
         * @param oauthServer
         */
        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
            oauthServer
                       .tokenKeyAccess("permitAll()")
                       .allowFormAuthenticationForClients();
        }

    }

}
