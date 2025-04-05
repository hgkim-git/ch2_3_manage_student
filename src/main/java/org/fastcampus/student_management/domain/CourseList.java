package org.fastcampus.student_management.domain;

import java.util.List;

public class CourseList {

  private final List<Course> courses;
  public CourseList(List<Course>courses) {
    this.courses = courses;
  }

  public void changeAllCoursesFee(int fee) {
    if (fee < 0) {
      throw new IllegalArgumentException("Fee cannot be negative");
    }
    for (Course course : courses) {
      DayOfWeek dayOfWeek = course.getDayOfWeek();
      if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
        fee = (int)(fee *1.5);
      }
      course.changeFee(fee);
    }
  }

}
