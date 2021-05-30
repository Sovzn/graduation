package com.syc.dao;

import com.syc.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Mapper
@Repository
public interface UserDao {
    //添加用户
void saveUser(User user);
   //根据用户名查询用户
User selUserByusername(String username);
   //查询所有用户
    List<User> selAllUser();
    //根据id删除用户
     void delUserByID(Integer id);
     //根据条件查询用户
     List<User> selUserByMap(Map map);
     //根据id查询用户
     User selUserById(Integer id);
     //更新用户
     void update(User user);
}
