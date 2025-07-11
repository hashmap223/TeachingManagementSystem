package com.ssmproject.service.impl;

import com.ssmproject.mapper.SelectedcourseMapper;
import com.ssmproject.mapper.StudentMapper;
import com.ssmproject.po.*;
import com.ssmproject.service.SelectedCourseService;
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
public class SelectedCourseServiceImpl implements SelectedCourseService {
    
    @Autowired
    private SelectedcourseMapper selectedcourseMapper;
    
    @Autowired
    private StudentMapper studentMapper;

//    @Resource(name = "courseServiceImpl")
//    private CourseService courseService;
    
    @Transactional(readOnly = true)
    public List<SelectedCourseCustom> findByCourseID(Integer id) throws Exception {
        
        SelectedcourseExample example = new SelectedcourseExample();
        SelectedcourseExample.Criteria criteria = example.createCriteria();
        criteria.andCourseidEqualTo(id);
        
        List<Selectedcourse> list = selectedcourseMapper.selectByExample(example);
        List<SelectedCourseCustom> secList = new ArrayList<SelectedCourseCustom>();
        for (Selectedcourse s : list) {
            SelectedCourseCustom sec = new SelectedCourseCustom();
            BeanUtils.copyProperties(s, sec);
            // 判断是否完成类该课程
            if (sec.getMark() != null) {
                sec.setOver(true);
            }
            Student student = studentMapper.selectByPrimaryKey(sec.getStudentid());
            StudentCustom studentCustom = new StudentCustom();
            BeanUtils.copyProperties(student, studentCustom);
            
            sec.setStudentCustom(studentCustom);
            
            secList.add(sec);
        }
        
        return secList;
    }


@Override
public List<SelectedCourseCustom> findByCourseIdUsername(Integer id, String userid, String username) throws Exception {
    SelectedcourseExample example = new SelectedcourseExample();
    SelectedcourseExample.Criteria criteria = example.createCriteria();
    criteria.andCourseidEqualTo(id);


    List<Selectedcourse> list = selectedcourseMapper.selectByExample(example);
    List<SelectedCourseCustom> secList = new ArrayList<>();

    for (Selectedcourse s : list) {
        SelectedCourseCustom sec = new SelectedCourseCustom();
        BeanUtils.copyProperties(s, sec);

        // 判断是否完成该课程
        if (sec.getMark() != null) {
            sec.setOver(true);
        }

        Student student = studentMapper.selectByPrimaryKey(sec.getStudentid());

        // 如果提供了 userid 或 username，则进行二次过滤
        boolean matchUserid = userid == null || userid.isEmpty() || student.getUserid().toString().contains(userid);
        boolean matchUsername = username == null || username.isEmpty() || student.getUsername().contains(username);

        if (matchUserid && matchUsername) {
            StudentCustom studentCustom = new StudentCustom();
            BeanUtils.copyProperties(student, studentCustom);
            sec.setStudentCustom(studentCustom);
            secList.add(sec);
        }
    }

    return secList;
}


    @Transactional(readOnly = true)
    public List<SelectedCourseCustom> findByCourseIDPaging(Integer page, Integer id) throws Exception {
        return null;
    }
    
    // 获取该课程学生数
    @Transactional(readOnly = true)
    public Integer countByCourseID(Integer id) throws Exception {
        SelectedcourseExample example = new SelectedcourseExample();
        SelectedcourseExample.Criteria criteria = example.createCriteria();
        criteria.andCourseidEqualTo(id);
        
        return selectedcourseMapper.countByExample(example);
    }
    
    // 查询指定学生成绩
    @Transactional(readOnly = true)
    public SelectedCourseCustom findOne(SelectedCourseCustom selectedCourseCustom) throws Exception {
        
        SelectedcourseExample example = new SelectedcourseExample();
        SelectedcourseExample.Criteria criteria = example.createCriteria();
        
        criteria.andCourseidEqualTo(selectedCourseCustom.getCourseid());
        criteria.andStudentidEqualTo(selectedCourseCustom.getStudentid());
        
        List<Selectedcourse> list = selectedcourseMapper.selectByExample(example);
        
        
        if (list.size() > 0) {
            SelectedCourseCustom sc = new SelectedCourseCustom();
            BeanUtils.copyProperties(list.get(0), sc);
            
            Student student = studentMapper.selectByPrimaryKey(selectedCourseCustom.getStudentid());
            StudentCustom studentCustom = new StudentCustom();
            BeanUtils.copyProperties(student, studentCustom);
            
            sc.setStudentCustom(studentCustom);
            
            return sc;
        }
        
        return null;
    }
    
    @Transactional
    public void updataOne(SelectedCourseCustom selectedCourseCustom) throws Exception {
        SelectedcourseExample example = new SelectedcourseExample();
        SelectedcourseExample.Criteria criteria = example.createCriteria();
        
        criteria.andCourseidEqualTo(selectedCourseCustom.getCourseid());
        criteria.andStudentidEqualTo(selectedCourseCustom.getStudentid());
        
        selectedcourseMapper.updateByExample(selectedCourseCustom, example);
        
    }
    
    @Transactional
    public void save(SelectedCourseCustom selectedCourseCustom) throws Exception {
        selectedcourseMapper.insert(selectedCourseCustom);
    }
    
    @Transactional(readOnly = true)
    public List<SelectedCourseCustom> findByStudentID(Integer id) throws Exception {
        return null;
    }
    
    @Transactional
    public void remove(SelectedCourseCustom selectedCourseCustom) throws Exception {
        SelectedcourseExample example = new SelectedcourseExample();
        SelectedcourseExample.Criteria criteria = example.createCriteria();
        
        criteria.andCourseidEqualTo(selectedCourseCustom.getCourseid());
        criteria.andStudentidEqualTo(selectedCourseCustom.getStudentid());
        
        selectedcourseMapper.deleteByExample(example);
    }
    
}
