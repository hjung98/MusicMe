package com.musicme.musicme.mappers;

import com.musicme.musicme.entities.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;


import java.util.List;
@Mapper
public interface UserMapper {
    @Select("select * from users")
    public List<User> findAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    public User findById(long id);

    @Delete("DELETE FROM users WHERE id = #{id}")
    public int deleteById(long id);

    @Insert("INSERT INTO users(id, username, firstName, lastName, email) " +
            " VALUES (#{id}, #{username} #{firstName}, #{lastName}, #{email})")
    public int insert(User user);

    @Update("Update users set firstName=#{firstName}, " +
            " lastName=#{lastName}, username=#{username}, email#{email} where id=#{id}")
    public int update(User user);
}