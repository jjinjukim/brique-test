package brique.brique_test.assignment3.dto;

import java.util.Date;

/**
 * Description : EmployeeProjection 클래스의 설명을 여기에 작성한다.
 * Date : 2/18/25
 * History :
 * - 작성자 : j, 날짜 :2/18/25, 설명 : 최초작성
 *
 * @author j
 * @version 1.0
 */
public interface EmployeeProjection {
    Integer getEmpNo();
    String getFirstName();
    String getLastName();
    String getGender();
    Date getHireDate();
    String getDeptName();
    String getTitle();
    Integer getMaxSalary();

    //로그 확인용
    default String toStringRepresentation() {
        return "EmployeeProjection{" +
                "empNo=" + getEmpNo() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", gender='" + getGender() + '\'' +
                ", hireDate=" + getHireDate() +
                ", deptName='" + getDeptName() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", maxSalary=" + getMaxSalary() +
                '}';
    }
}
