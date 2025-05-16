package org.fastcampus.student_management.application.course.interfaces;

import java.util.List;
import org.fastcampus.student_management.domain.Course;
import org.fastcampus.student_management.domain.DayOfWeek;

// 조회 담당 Repository 인터페이스
public interface CourseQueryRepository {

  List<Course> getCourseDayOfWeek(DayOfWeek dayOfWeek);

  List<Course> getCourseListByStudent(String studentName);
}
