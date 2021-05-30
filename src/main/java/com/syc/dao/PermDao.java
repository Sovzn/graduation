package com.syc.dao;

import com.syc.pojo.Perm;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface PermDao {
    //根据角色id查权限
    List<Perm> getPerms( Integer rid);
}
