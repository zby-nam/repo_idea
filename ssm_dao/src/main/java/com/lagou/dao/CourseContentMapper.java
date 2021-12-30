package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {

    //询课程下的章节与课时信息

    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);

    //回显章节对应的课程信息
    public Course findCourseByCourseId(int courseId);

    //保存章节 saveSection
    public void saveSection(CourseSection section);

    void updateSection(CourseSection section);

    //修改章节状态
    public void updateSectionStatus(CourseSection section);

    //新建课时信息
    public void saveLesson(CourseLesson lesson);

    void updateLesson(CourseLesson lesson);
}
