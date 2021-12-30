package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {

    //根据课程id查询对应的课程信息
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);

    //回显章节对应的课程信息
    public Course findCourseByCourseId(int courseId);

    //保存章节
    public void saveSection(CourseSection section);

    void updateSection(CourseSection section);

    public void updateSectionStatus(int id, int status);

    //新增课时信息
    public void saveLesson(CourseLesson lesson);

    void updateLesson(CourseLesson lesson);
}
