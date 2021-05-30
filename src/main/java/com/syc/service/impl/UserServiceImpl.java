package com.syc.service.impl;

import com.syc.dao.UserDao;
import com.syc.pojo.User;
import com.syc.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public void register(User user) {//添加用户
        //加密密码：md5+盐+散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),"sovzn+shiyaochang",1024);
        //将加密后的密码赋给用户
         user.setPassword(md5Hash.toHex());
         userDao.saveUser(user);
    }

    @Override
    public User selUserByusername(String username) {
        return userDao.selUserByusername(username);
    }

    @Override
    public List<User> selAllUser() {
        return userDao.selAllUser();
    }

    @Override
    public void delUserByID(Integer id) {
        userDao.delUserByID(id);
        return;
    }

    @Override
    public List<User> selUserByMap(Map map) {
        return userDao.selUserByMap(map);
    }

    @Override
    public User selUserById(Integer id) {
        return userDao.selUserById(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }
}
