package cn.ma.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import cn.ma.bean.User;

@Mapper
public interface UserMapper {

    @Select("select id from user where name = #{username} and passwd = #{passwd}")
    long login(User user);
}