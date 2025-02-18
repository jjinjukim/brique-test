package brique.brique_test.assignment3.service;

import brique.brique_test.assignment3.dto.EmployeeProjection;
import brique.brique_test.assignment3.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description : EmployeeServiceImpl 클래스의 설명을 여기에 작성한다.
 * Date : 2/18/25
 * History :
 * - 작성자 : j, 날짜 :2/18/25, 설명 : 최초작성
 *
 * @author j
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeProjection> getEmployeeList() {
        return employeeRepository.findEmployeeList();
    }
}
