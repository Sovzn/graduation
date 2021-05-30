package com.syc.service.impl;

import com.syc.dao.PermDao;
import com.syc.pojo.Perm;
import com.syc.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermServiceImpl  implements PermService {
    @Autowired
    PermDao permDao;
    @Override
    public List<Perm> getPerms(Integer rid) {
        return permDao.getPerms(rid);
    }
}
