package brique.brique_test.assignment5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description : RandomResponseAggregator 클래스의 설명을 여기에 작성한다.
 * Date : 2/18/25
 * History :
 * - 작성자 : j, 날짜 :2/18/25, 설명 : 최초작성
 *
 * @author j
 * @version 1.0
 */
public class RandomResponseAggregator {
    private final RandomResponseClient client;
    private final int callCount;

    public RandomResponseAggregator(RandomResponseClient client, int callCount) {
        this.client = client;
        this.callCount = callCount;
    }

    /**
     * 지정한 횟수만큼 호출하여 응답의 빈도를 집계합니다.
     */
    public Map<String, Integer> aggregateResponses() throws Exception {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < callCount; i++) {
            String response = client.fetchResponse();
            frequencyMap.put(response, frequencyMap.getOrDefault(response, 0) + 1);
        }
        return frequencyMap;
    }

    /**
     * 집계된 결과를 응답 빈도수 기준 내림차순으로 정렬하여 출력합니다.
     */
    public void printSortedResults() throws Exception {
        Map<String, Integer> frequencyMap = aggregateResponses();
        int total = frequencyMap.values().stream().mapToInt(Integer::intValue).sum();

        List<Map.Entry<String, Integer>> sortedEntries = frequencyMap.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toList());

        for (Map.Entry<String, Integer> entry : sortedEntries) {
            System.out.println("count: " + entry.getValue() + " " + entry.getKey());
        }
        System.out.println("Total count: " + total);
    }
}
