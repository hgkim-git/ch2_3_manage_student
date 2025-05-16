package org.fastcampus.student_management.application.course;

import java.util.List;
import java.util.stream.Collectors;
import org.fastcampus.student_management.application.course.dto.CourseInfoDto;
import org.fastcampus.student_management.application.course.interfaces.CourseCommandRepository;
import org.fastcampus.student_management.application.course.interfaces.CourseQueryRepository;
import org.fastcampus.student_management.application.student.StudentService;
import org.fastcampus.student_management.domain.Course;
import org.fastcampus.student_management.domain.CourseList;
import org.fastcampus.student_management.domain.DayOfWeek;
import org.fastcampus.student_management.domain.Student;

public class CourseService {

  /**
   * CourseService는 강의 도메인을 처리하는 주요 로직이 들어 있는 고수준 컴포넌트임 그런데 여기에서 비즈니스 로직이 포함되지 않은 Repository 관련된 저수준
   * 컴포넌트인 CourseRepository 클래스를 의존하고 있음. 이는 앞서 이론 강의때 배운 클린 아키텍쳐에 위배됨 또한 DB의 형태가 변경되면 직접적인 코드 수정이
   * 필요함(기존 `CourseRepository`를 의존하고 있는 모든 코드를 변경해야 함) 따라서 인터페이스를 만들어서 레이어의 경계를 좀더 분명하게 한다
   */
  private final CourseCommandRepository courseCommandRepository;
  private final CourseQueryRepository courseQueryRepository;
  private final StudentService studentService;

  public CourseService(CourseCommandRepository courseCommandRepository,
      CourseQueryRepository courseQueryRepository, StudentService studentService) {
    this.courseCommandRepository = courseCommandRepository;
    this.courseQueryRepository = courseQueryRepository;
    this.studentService = studentService;
  }

  public void registerCourse(CourseInfoDto courseInfoDto) {
    Student student = studentService.getStudent(courseInfoDto.getStudentName());
    Course course = new Course(student, courseInfoDto.getCourseName(), courseInfoDto.getFee(),
        courseInfoDto.getDayOfWeek(), courseInfoDto.getCourseTime());
    courseCommandRepository.save(course);
  }

  public List<CourseInfoDto> getCourseDayOfWeek(DayOfWeek dayOfWeek) {
    // TODO: 과제 구현 부분
    List<Course> courses = courseQueryRepository.getCourseDayOfWeek(dayOfWeek);
    return courses.stream().map(CourseInfoDto::new).collect(Collectors.toList());
  }

  public void changeFee(String studentName, int fee) {
    // TODO: 과제 구현 부분
    Student student = studentService.getStudent(studentName);
    if (student == null) {
      throw new IllegalArgumentException("Student not found");
    }
    List<Course> courses = courseQueryRepository.getCourseListByStudent(student.getName());
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
