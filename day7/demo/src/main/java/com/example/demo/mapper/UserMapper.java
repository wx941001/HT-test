package com.example.demo.mapper;

import com.example.demo.Dom.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * mapper的具体表达式
 */
@Mapper //标记mapper文件位置，否则在Application.class启动类上配置mapper包扫描
@Repository
public interface UserMapper {

    /**
     * 查询用户名是否存在，若存在，不允许注册
     */
    @Select(value = "select u.username,u.pwd from user_for_login u where u.username=#{username} and u.pwd=pwd")
    @Results
            ({@Result(property = "username",column = "username"),
                    @Result(property = "pwd",column = "pwd")})
    User selectUserByName(@Param("username") String username);
    /**
     * 注册  插入一条user记录
     */
    @Insert("insert into user_for_login(username,pwd) values(#{username},#{pwd})")
    void insertUser(User user);
    /**
     * 登录
     */
    @Select("select u.id from user_for_login u where u.username = #{username} and u.pwd = #{pwd}")
    Long login(User user);
}
