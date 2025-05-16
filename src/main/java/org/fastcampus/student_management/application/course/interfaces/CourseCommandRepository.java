package org.fastcampus.student_management.application.course.interfaces;

import org.fastcampus.student_management.domain.Course;

// 조회 외 DB 명령관련 인터페이스 별도 생성(CQRS 개념 찾아보기)
public interface CourseCommandRepository {

  void save(Course course);
}
