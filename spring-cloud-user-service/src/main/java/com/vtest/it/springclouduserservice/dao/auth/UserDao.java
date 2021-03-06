package com.vtest.it.springclouduserservice.dao.auth;

import common.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    public User getUser(@Param("username") String username);

    public List<User> getAllUser();

    public void removeUser(@Param("username") String username);

    public void updateUser(User userAdd);

    public int register(User userNew);
}
