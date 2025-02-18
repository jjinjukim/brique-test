package brique.brique_test.assignment1;

import java.util.stream.Stream;

/**
 * Description : Assignment1Main
 * Date : 2/17/25
 * History :
 * - 작성자 : j, 날짜 :2/17/25, 설명 : 최초작성
 *
 * @author j
 * @version 1.0
 */
public class Assignment1 {
    public static void main(String[] args) {
        // CSV 파일 경로 지정 (프로젝트 루트 기준 상대경로)
        // 클래스패스 기준 경로로 지정 (src/main/resources/csv/sample.csv)
        String filePath = "csv/sample.csv";

        // CSV 파일 읽기
        CsvReader reader = new CsvReader();

        // try-with-resources를 사용하여 스트림을 안전하게 닫습니다.
        try (Stream<String> linesStream = reader.readCsvStream(filePath)) {

            // 이후 CsvCalculator 등에서 List<String> 기반으로 처리할 수 있습니다.
            CsvCalculator calculator = new CsvCalculator();
            calculator.processLines(linesStream);
        }
    }
}
