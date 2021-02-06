package cn.ma.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.ma.bean.User;

@Mapper
public interface UserMapper {

    // 查找是否存在重名用户
    @Select("select name, passwd from user where name = #{username}")
    @Results({ @Result(property = "username", column = "name"), @Result(property = "passwd", column = "passwd") })
    User findUser(@Param("username") String username);

    // 注册
    @Insert("insert into user(name,passwd) values(#{username},#{passwd})")
    Void register(User user);

    // 查找新建用户分配的ID
    @Select("select id from user where name = #{username}")
    Long findID(@Param("username") String username);

    // 登录
    @Select("select id from user where name = #{username} and passwd = #{passwd}")
    Long login(User user);
}