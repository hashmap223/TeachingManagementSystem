package com.ssmproject.service;

import com.ssmproject.po.Role;
/**
 * Role 权限表Service层
 * @author 
 *
 */
public interface RoleService {

    Role findByid(Integer id) throws Exception;

}
