package com.ssmproject.mapper;

import com.ssmproject.po.UserloginCustom;

/**
 *  UserloginMapper扩展类
 */
public interface UserloginMapperCustom {

    UserloginCustom findOneByName(String name) throws Exception;

}
