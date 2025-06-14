package com.ssmproject.controller;

import com.ssmproject.exception.CustomException;
import com.ssmproject.po.CourseCustom;
import com.ssmproject.po.PagingVO;
import com.ssmproject.po.SelectedCourseCustom;
import com.ssmproject.po.StudentCustom;
import com.ssmproject.service.CourseService;
import com.ssmproject.service.SelectedCourseService;
import com.ssmproject.service.StudentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author HashMap
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {
    
    @Resource(name = "courseServiceImpl")
    private CourseService courseService;
    
    @Resource(name = "studentServiceImpl")
    private StudentService studentService;
    
    @Resource(name = "selectedCourseServiceImpl")
    private SelectedCourseService selectedCourseService;
    
    
    // 搜索课程
    @RequestMapping(value = "selectCourse", method = {RequestMethod.POST})
    private String selectCourse(String findByName, Model model) throws Exception {
        
        List<CourseCustom> list = courseService.findByName(findByName);
        
        model.addAttribute("courseList", list);
        return "student/showCourse";
    }
    
    
    @RequestMapping(value = "/showCourse")
    public String stuCourseShow(Model model, Integer page) throws Exception {
        
        List<CourseCustom> list = null;
        // 页码对象
        PagingVO pagingVO = new PagingVO();
        // 设置总页数
        pagingVO.setTotalCount(courseService.getCountCouse());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = courseService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = courseService.findByPaging(page);
        }
        
        model.addAttribute("courseList", list);
        model.addAttribute("pagingVO", pagingVO);
        
        return "student/showCourse";
    }
    
    // 选课操作
    @RequestMapping(value = "/stuSelectedCourse")
    public String stuSelectedCourse(int id) throws Exception {
        System.out.println(id);
        // 获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        
        SelectedCourseCustom selectedCourseCustom = new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(id);
        selectedCourseCustom.setStudentid(Integer.parseInt(username));
        
        SelectedCourseCustom s = selectedCourseService.findOne(selectedCourseCustom);
        if (s == null) {
            selectedCourseService.save(selectedCourseCustom);
        } else {
            throw new CustomException("该门课程你已经选了，不能再选");
        }
        
        return "redirect:/student/selectedCourse";
    }
    
    // 退课操作
    @RequestMapping(value = "/outCourse")
    public String outCourse(int id) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        
        SelectedCourseCustom selectedCourseCustom = new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(id);
        selectedCourseCustom.setStudentid(Integer.parseInt(username));
        
        selectedCourseService.remove(selectedCourseCustom);
        
        return "redirect:/student/selectedCourse";
    }
    
    // 已选课程
    @RequestMapping(value = "/selectedCourse")
    public String selectedCourse(Model model) throws Exception {
        // 获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        StudentCustom studentCustom = studentService.findStudentAndSelectCourseListByName((String) subject.getPrincipal());
        
        List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();
        
        model.addAttribute("selectedCourseList", list);
        
        return "student/selectCourse";
    }
    
    // 已修课程
    // 根据课程名称查询成绩
    @RequestMapping(value = "/overCourse", method = {RequestMethod.POST, RequestMethod.GET})
    private String overCourse(String findByName, Model model) throws Exception {
        // 获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        StudentCustom studentCustom = studentService.findStudentAndSelectCourseListByName((String) subject.getPrincipal());
        
        List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();
        // 转换为流根据条件过滤
        if (findByName != null && !findByName.trim().isEmpty()) {
            list = list
                    .stream()
                    .filter(c -> containsIgnoreCase(c.getCouseCustom().getCoursename(), findByName))
                    .collect(Collectors.toList());
        }
        
        model.addAttribute("selectedCourseList", list);
        return "student/overCourse";
    }
    // 模糊查询子串
    public boolean containsIgnoreCase(String source, String target) {
        if (source == null || target == null) {
            return false;
        }
        return source.toLowerCase().contains(target.toLowerCase());
    }
    
    // 修改密码
    @RequestMapping(value = "/passwordRest")
    public String passwordRest() throws Exception {
        return "student/passwordRest";
    }
    

    
}
