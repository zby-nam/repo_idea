package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;

import java.util.List;

public interface CourseMapper {

    //对条件课程列表查询
    public List<Course> findCourseByCondition(CourseVO courseVO);

    //新增课程信息
    public void saveCourse(Course course);

    //新增讲师信息
    public void saveTeacher(Teacher teacher);

    //根据id获取课程信息
    public CourseVO findCourseById(int id);

    //修改课程信息
    public void updateCourse(Course course);

    //修改讲师信息
    public void updateTeacher(Teacher teacher);

    //修改课程上下架状态
    public void updateCourseStatus(Course course);


}
