package org.fastcampus.student_management.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.fastcampus.student_management.application.course.interfaces.CourseQueryRepository;
import org.fastcampus.student_management.domain.Course;
import org.fastcampus.student_management.domain.DayOfWeek;

public class CourseJdbcQueryRepositoryImpl implements CourseQueryRepository {

  private final Map<String, Course> courseMap = new HashMap<>();

  @Override
  public List<Course> getCourseDayOfWeek(DayOfWeek dayOfWeek) {
    List<Course> courses = new ArrayList<>();
    for (Course course : courseMap.values()) {
      if (course.isSameDay(dayOfWeek) && course.isActivateUser()) {
        courses.add(course);
      }
    }
    return courses;
  }

  @Override
  public List<Course> getCourseListByStudent(String studentName) {
    List<Course> courses = new ArrayList<>();
    for (Course course : courseMap.values()) {
      if (course.getStudentName().equals(studentName)) {
        courses.add(course);
      }
    }
    return courses;
  }
}
