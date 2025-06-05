package com.ssmproject.service.impl;

import com.ssmproject.exception.CustomException;
import com.ssmproject.mapper.CollegeMapper;
import com.ssmproject.mapper.CourseMapper;
import com.ssmproject.mapper.TeacherMapper;
import com.ssmproject.mapper.TeacherMapperCustom;
import com.ssmproject.po.*;
import com.ssmproject.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HashMap
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    
    @Autowired
    private TeacherMapper teacherMapper;
    
    @Autowired
    private TeacherMapperCustom teacherMapperCustom;
    
    @Autowired
    private CollegeMapper collegeMapper;
    
    @Autowired
    private CourseMapper courseMapper;
    
    @Transactional
    public void updateById(Integer id, TeacherCustom teacherCustom) throws Exception {
        teacherMapper.updateByPrimaryKey(teacherCustom);
    }
    
    @Transactional
    public void removeById(Integer id) throws Exception {
        CourseExample courseExample = new CourseExample();
        
        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andTeacheridEqualTo(id);
        List<Course> list = courseMapper.selectByExample(courseExample);
        
        if (list.size() != 0) {
            throw new CustomException("请先删除该名老师所教授的课程");
        }
        
        teacherMapper.deleteByPrimaryKey(id);
    }
    
    @Transactional(readOnly = true)
    public List<TeacherCustom> findByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        
        List<TeacherCustom> list = teacherMapperCustom.findByPaging(pagingVO);
        
        return list;
    }
    
    @Transactional
    public Boolean save(TeacherCustom teacherCustom) throws Exception {
        
        Teacher tea = teacherMapper.selectByPrimaryKey(teacherCustom.getUserid());
        if (tea == null) {
            teacherMapper.insert(teacherCustom);
            return true;
        }
        return false;
    }
    
    @Transactional(readOnly = true)
    public int getCountTeacher() throws Exception {
        // 自定义查询对象
        TeacherExample teacherExample = new TeacherExample();
        // 通过criteria构造查询条件
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andUseridIsNotNull();
        
        return teacherMapper.countByExample(teacherExample);
    }
    
    @Transactional(readOnly = true)
    public TeacherCustom findById(Integer id) throws Exception {
        Teacher teacher = teacherMapper.selectByPrimaryKey(id);
        TeacherCustom teacherCustom = null;
        if (teacher != null) {
            teacherCustom = new TeacherCustom();
            BeanUtils.copyProperties(teacher, teacherCustom);
        }
        
        return teacherCustom;
    }
    
    @Transactional(readOnly = true)
    public List<TeacherCustom> findByName(String name) throws Exception {
        TeacherExample teacherExample = new TeacherExample();
        // 自定义查询条件
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        
        criteria.andUsernameLike("%" + name + "%");
        
        List<Teacher> list = teacherMapper.selectByExample(teacherExample);
        
        List<TeacherCustom> teacherCustomList = null;
        
        if (list != null) {
            teacherCustomList = new ArrayList<TeacherCustom>();
            for (Teacher t : list) {
                TeacherCustom teacherCustom = new TeacherCustom();
                // 类拷贝
                BeanUtils.copyProperties(t, teacherCustom);
                // 获取课程名
                College college = collegeMapper.selectByPrimaryKey(t.getCollegeid());
                teacherCustom.setcollegeName(college.getCollegename());
                
                teacherCustomList.add(teacherCustom);
            }
        }
        
        return teacherCustomList;
    }
    
    @Transactional(readOnly = true)
    public List<TeacherCustom> findAll() throws Exception {
        
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        
        criteria.andUsernameIsNotNull();
        
        List<Teacher> list = teacherMapper.selectByExample(teacherExample);
        List<TeacherCustom> teacherCustomsList = null;
        if (list != null) {
            teacherCustomsList = new ArrayList<TeacherCustom>();
            for (Teacher t : list) {
                TeacherCustom teacherCustom = new TeacherCustom();
                BeanUtils.copyProperties(t, teacherCustom);
                teacherCustomsList.add(teacherCustom);
            }
        }
        return teacherCustomsList;
    }
}
