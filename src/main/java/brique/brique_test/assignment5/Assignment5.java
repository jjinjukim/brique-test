package brique.brique_test.assignment5;

/**
 * Description : Assignment5 클래스의 설명을 여기에 작성한다.
 * Date : 2/18/25
 * History :
 * - 작성자 : j, 날짜 :2/18/25, 설명 : 최초작성
 *
 * @author j
 * @version 1.0
 */
public class Assignment5 {
    public static void main(String[] args) {
        String url = "http://codingtest.brique.kr:8080/random";
        RandomResponseClient client = new RandomResponseClient(url);
        RandomResponseAggregator aggregator = new RandomResponseAggregator(client, 100);
        try {
            aggregator.printSortedResults();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
