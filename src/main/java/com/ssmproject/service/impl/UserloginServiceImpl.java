package com.ssmproject.service.impl;

import com.ssmproject.mapper.UserloginMapper;
import com.ssmproject.po.Userlogin;
import com.ssmproject.po.UserloginExample;
import com.ssmproject.service.UserloginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author HashMap
 */
@Service
public class UserloginServiceImpl implements UserloginService {
    
    @Autowired
    private UserloginMapper userloginMapper;
    
    @Transactional(readOnly = true)
    public Userlogin findByName(String name) throws Exception {
        UserloginExample userloginExample = new UserloginExample();
        
        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUsernameEqualTo(name);
        
        List<Userlogin> list = userloginMapper.selectByExample(userloginExample);
        
        return list.get(0);
    }
    
    @Transactional
    public void save(Userlogin userlogin) throws Exception {
        userloginMapper.insert(userlogin);
    }
    
    @Transactional
    public void removeByName(String name) throws Exception {
        UserloginExample userloginExample = new UserloginExample();
        
        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUsernameEqualTo(name);
        
        userloginMapper.deleteByExample(userloginExample);
    }
    
    @Transactional
    public void updateByName(String name, Userlogin userlogin) {
        UserloginExample userloginExample = new UserloginExample();
        
        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUsernameEqualTo(name);
        
        userloginMapper.updateByExample(userlogin, userloginExample);
    }
    
}
