package org.fastcampus.student_management.application.course;

import java.util.List;
import java.util.stream.Collectors;
import org.fastcampus.student_management.application.course.dto.CourseInfoDto;
import org.fastcampus.student_management.application.student.StudentService;
import org.fastcampus.student_management.domain.Course;
import org.fastcampus.student_management.domain.CourseList;
import org.fastcampus.student_management.domain.DayOfWeek;
import org.fastcampus.student_management.domain.Student;
import org.fastcampus.student_management.repo.CourseRepository;

public class CourseService {
  private final CourseRepository courseRepository;
  private final StudentService studentService;

  public CourseService(CourseRepository courseRepository, StudentService studentService) {
    this.courseRepository = courseRepository;
    this.studentService = studentService;
  }

  public void registerCourse(CourseInfoDto courseInfoDto) {
    Student student = studentService.getStudent(courseInfoDto.getStudentName());
    Course course = new Course(student, courseInfoDto.getCourseName(), courseInfoDto.getFee(), courseInfoDto.getDayOfWeek(), courseInfoDto.getCourseTime());
    courseRepository.save(course);
  }

  public List<CourseInfoDto> getCourseDayOfWeek(DayOfWeek dayOfWeek) {
    // TODO: 과제 구현 부분
    List<Course> courses = courseRepository.getCourseDayOfWeek(dayOfWeek);
    return courses.stream().map(CourseInfoDto::new).collect(Collectors.toList());
  }

  public void changeFee(String studentName, int fee) {
    // TODO: 과제 구현 부분
    Student student = studentService.getStudent(studentName);
    if (student == null) {
      throw new IllegalArgumentException("Student not found");
    }
    List<Course> courses = courseRepository.getCourseListByStudent(student.getName());
//    courses.forEach(course -> course.changeFee(fee));
//    더 나아가기
//        1. 강의 객체 하위의 수강료 객체를 조작하는 부분이 서비스 레이어까지 노출되게 됨
//        2. 강의 컬렉션을 조작하는 비즈니스 로직이 추가될 가능성 있음
//        3. 비즈니스 로직이 더 많아지면 비즈니스 로직하나 테스트 하려면 프로그램 실행한 뒤 직접 입력해서 테스트해야 해서 번거로움 -> 더미들로만 테스트 가능할도록 할 필요성
//    => 1급 컬렉션을 만들어 전체 강의 컬렉션의 수강료를 수정할 수 있게끔 하고 추후 강의 컬렉션 대상으로한 비즈니스 로직 추가시 수정이 용이하도록 함, 수강료 객체를 강의 서비스 레이어로부터 숨길 수 있음

    CourseList list = new CourseList(courses);
    list.changeAllCoursesFee(fee);
  }
}
