package com.ssmproject.service.impl;

import com.ssmproject.mapper.CollegeMapper;
import com.ssmproject.mapper.StudentMapper;
import com.ssmproject.mapper.StudentMapperCustom;
import com.ssmproject.po.*;
import com.ssmproject.service.StudentService;
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
public class StudentServiceImpl implements StudentService {
    
    // 使用spring 自动注入
    @Autowired
    private StudentMapperCustom studentMapperCustom;
    
    @Autowired
    private StudentMapper studentMapper;
    
    @Autowired
    private CollegeMapper collegeMapper;
    
    @Transactional
    public void updataById(Integer id, StudentCustom studentCustom) throws Exception {
        studentMapper.updateByPrimaryKey(studentCustom);
    }
    
    @Transactional
    public void removeById(Integer id) throws Exception {
        studentMapper.deleteByPrimaryKey(id);
    }
    
    @Transactional(readOnly = true)
    public List<StudentCustom> findByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);
        
        List<StudentCustom> list = studentMapperCustom.findByPaging(pagingVO);
        
        return list;
    }
    
    @Transactional
    public Boolean save(StudentCustom studentCustoms) throws Exception {
        Student stu = studentMapper.selectByPrimaryKey(studentCustoms.getUserid());
        if (stu == null) {
            studentMapper.insert(studentCustoms);
            return true;
        }
        
        return false;
    }
    
    // 返回学生总数
    public int getCountStudent() throws Exception {
        // 自定义查询对象
        StudentExample studentExample = new StudentExample();
        // 通过criteria构造查询条件
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andUseridIsNotNull();
        
        return studentMapper.countByExample(studentExample);
    }
    
    public StudentCustom findById(Integer id) throws Exception {
        
        Student student = studentMapper.selectByPrimaryKey(id);
        StudentCustom studentCustom = null;
        if (student != null) {
            studentCustom = new StudentCustom();
            // 类拷贝
            BeanUtils.copyProperties(student, studentCustom);
        }
        
        return studentCustom;
    }
    
    // 模糊查询
    public List<StudentCustom> findByName(String name) throws Exception {
        
        StudentExample studentExample = new StudentExample();
        // 自定义查询条件
        StudentExample.Criteria criteria = studentExample.createCriteria();
        
        criteria.andUsernameLike("%" + name + "%");
        
        List<Student> list = studentMapper.selectByExample(studentExample);
        
        List<StudentCustom> studentCustomList = null;
        
        if (list != null) {
            studentCustomList = new ArrayList<StudentCustom>();
            for (Student s : list) {
                StudentCustom studentCustom = new StudentCustom();
                // 类拷贝
                BeanUtils.copyProperties(s, studentCustom);
                // 获取课程名
                College college = collegeMapper.selectByPrimaryKey(s.getCollegeid());
                studentCustom.setcollegeName(college.getCollegename());
                
                studentCustomList.add(studentCustom);
            }
        }
        
        return studentCustomList;
    }
    
    public StudentCustom findStudentAndSelectCourseListByName(String name) throws Exception {
        
        StudentCustom studentCustom = studentMapperCustom.findStudentAndSelectCourseListById(Integer.parseInt(name));
        
        List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();
        
        // 判断该课程是否修完
        for (SelectedCourseCustom s : list) {
            if (s.getMark() != null) {
                s.setOver(true);
            }
        }
        return studentCustom;
    }
}
