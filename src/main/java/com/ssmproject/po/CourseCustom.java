package com.ssmproject.po;

/**
 * Course扩展类    课程
 * @author HashMap
 */
public class CourseCustom extends Course {

    //所属院系名
    private String collegeName;

    public void setcollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getcollegeName() {
        return collegeName;
    }

}
