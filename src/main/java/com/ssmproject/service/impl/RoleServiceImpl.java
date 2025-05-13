package com.ssmproject.service.impl;

import com.ssmproject.mapper.RoleMapper;
import com.ssmproject.po.Role;
import com.ssmproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HashMap
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public Role findByid(Integer id) throws Exception {
        return roleMapper.selectByPrimaryKey(id);
    }
}
