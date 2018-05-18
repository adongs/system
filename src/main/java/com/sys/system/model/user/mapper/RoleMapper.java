package com.sys.system.model.user.mapper;

import com.sys.system.model.user.entity.Role;
import com.sys.system.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author yudong
 * @Date 2018年05月14日 上午7:16
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {


    /**
     * 根据用户id查询出用户所有的角色
     * @param userId
     * @return
     */
    @Select("select \n" +
            "r.role\n" +
            "from  user_role ru\n" +
            "left join role r\n" +
            "on ru.role_id = r.id\n" +
            "where ru.user_id = #{userId}")
     List<String> findByUserId(@Param("userId") int userId);
}
