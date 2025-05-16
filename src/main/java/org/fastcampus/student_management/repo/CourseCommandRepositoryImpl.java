package org.fastcampus.student_management.repo;

import java.util.HashMap;
import java.util.Map;
import org.fastcampus.student_management.domain.Course;

// 인메모리에서는 저장만(조회는 CourseJdbcRepository 에서)
public class CourseCommandRepositoryImpl implements
    org.fastcampus.student_management.application.course.interfaces.CourseCommandRepository {

  private final Map<String, Course> courseMap = new HashMap<>();

  public void save(Course course) {
    courseMap.put(course.getCourseName(), course);
  }

}
