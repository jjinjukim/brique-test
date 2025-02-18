package brique.brique_test.assignment3.repository;

import brique.brique_test.assignment3.dto.EmployeeProjection;
import brique.brique_test.assignment3.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Description : EmployeeRepository 클래스의 설명을 여기에 작성한다.
 * Date : 2/18/25
 * History :
 * - 작성자 : j, 날짜 :2/18/25, 설명 : 최초작성
 *
 * @author j
 * @version 1.0
 */
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    @Query(value = "SELECT e.emp_no AS empNo, " +
            "       e.first_name AS firstName, " +
            "       e.last_name AS lastName, " +
            "       e.gender AS gender, " +
            "       e.hire_date AS hireDate, " +
            "       d.dept_name AS deptName, " +
            "       t.title AS title, " +
            "       MAX(s.salary) AS maxSalary " +
            "FROM employees e " +
            "INNER JOIN salaries s ON e.emp_no = s.emp_no " +
            "INNER JOIN titles t ON e.emp_no = t.emp_no " +
            "INNER JOIN dept_emp de ON e.emp_no = de.emp_no " +
            "INNER JOIN departments d ON de.dept_no = d.dept_no " +
            "WHERE e.hire_date >= '2000-01-01' " +
            "GROUP BY e.emp_no, e.first_name, e.last_name, e.gender, e.hire_date, d.dept_name, t.title " +
            "ORDER BY e.emp_no", nativeQuery = true)
    List<EmployeeProjection> findEmployeeList();
}
