package com.syc.service;

import com.syc.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    void register(User user);//添加用户
    User selUserByusername(String username);//根据用户名查用户
    List<User> selAllUser();//查询所有用户
    void delUserByID(Integer id);//根据id删除用户
    List<User> selUserByMap(Map map);//根据条件查询用户
    User selUserById(Integer id);//根据id查询用户
    void update(User user);//更新用户
}
