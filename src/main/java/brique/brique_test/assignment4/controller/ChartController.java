package brique.brique_test.assignment4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description : ChartController 클래스의 설명을 여기에 작성한다.
 * Date : 2/18/25
 * History :
 * - 작성자 : j, 날짜 :2/18/25, 설명 : 최초작성
 *
 * @author j
 * @version 1.0
 */
@Controller
public class ChartController {
    @GetMapping("/chart/api/graph")
    public ModelAndView showGraph() {
        return new ModelAndView("assignment4/chart");
    }
}
