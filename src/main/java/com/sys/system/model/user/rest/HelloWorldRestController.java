package com.sys.system.model.user.rest;

import com.alibaba.fastjson.JSON;
import com.sys.system.model.user.entity.User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author yudong
 * @Date 2018年05月03日 上午10:27
 */
@RestController
public class HelloWorldRestController {


    @RequestMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @RequestMapping("/order/{id}")
    public String getOrder(@RequestBody @Validated User user) {
        //for debug
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        System.out.println(userDetails.getUsername());
        System.out.println(JSON.toJSONString(userDetails.getAuthorities()));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : 1" ;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String exit(HttpServletResponse response, AccessDeniedException a){
        response.setStatus(HttpServletResponse.SC_GONE);
        System.out.println(a.getMessage());
        return "权限不足";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String exits(HttpServletResponse response, MethodArgumentNotValidException a){
        return a.getBindingResult().getFieldError().getDefaultMessage();
    }

}
