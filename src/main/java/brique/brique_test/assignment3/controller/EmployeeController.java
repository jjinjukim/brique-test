package brique.brique_test.assignment3.controller;

import brique.brique_test.assignment3.dto.EmployeeProjection;
import brique.brique_test.assignment3.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Description : EmployeeController 클래스의 설명을 여기에 작성한다.
 * Date : 2/18/25
 * History :
 * - 작성자 : j, 날짜 :2/18/25, 설명 : 최초작성
 *
 * @author j
 * @version 1.0
 */
//@RestController //JSON 데이터 반환
@Controller
@RequestMapping("/employee/api")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/list")
    public ModelAndView employeeList() {
        log.info("EmployeeController: Received request for employee list");

        // employeeService로부터 리스트를 가져옴 (한 번만 호출)
        List<EmployeeProjection> employeeList = employeeService.getEmployeeList();
        log.info("EmployeeController: Retrieved {} employees", employeeList.size());

        // 각 직원 정보를 로그에 출력 (Projection 인터페이스에 toStringRepresentation() 메서드가 정의되어 있어야 함)
        employeeList.forEach(emp -> log.info("Employee: {}", emp.toStringRepresentation()));

        ModelAndView mav = new ModelAndView("assignment3/employee");
        mav.addObject("employeeList", employeeList);

        return mav;
    }
}
