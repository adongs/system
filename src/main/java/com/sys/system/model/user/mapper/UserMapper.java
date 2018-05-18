package com.sys.system.model.user.mapper;

import com.sys.system.model.user.entity.User;
import com.sys.system.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author yudong
 * @Date 2018年05月14日 上午7:16
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


    @Select("select * from user where name=#{name}")
    User selectOneByName(@Param("name") String name);
}
