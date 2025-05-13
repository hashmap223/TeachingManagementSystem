package com.ssmproject.mapper;

import com.ssmproject.po.PagingVO;
import com.ssmproject.po.TeacherCustom;

import java.util.List;

public interface TeacherMapperCustom {

    //分页查询老师信息
    List<TeacherCustom> findByPaging(PagingVO pagingVO) throws Exception;

    //获取
}
