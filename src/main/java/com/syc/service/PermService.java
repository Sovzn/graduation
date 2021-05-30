package com.syc.service;

import com.syc.pojo.Perm;

import java.util.List;

public interface PermService {
    //根据角色查权限
    List<Perm> getPerms(Integer rid);
}
