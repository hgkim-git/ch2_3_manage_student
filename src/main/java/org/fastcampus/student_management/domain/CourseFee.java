package org.fastcampus.student_management.domain;

public class CourseFee {

  private int fee;

  public CourseFee(int fee) {
    this.fee = fee;
  }

  public int getFee() {
    return fee;
  }

  public void changeFee(int fee) {
    // 수강료를 객체로 관리함으로써 수강료 음수 체크를 여기저기서 불필요하게 할 필요가 없도록 한다
    if (fee < 0) {
      throw new IllegalArgumentException("Fee cannot be negative");
    }
    this.fee = fee;
  }
}
