package brique.brique_test.assignment1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
public class Assignment1Main {
    public static void main(String[] args) {
        // CSV 파일 경로 지정 (프로젝트 루트 기준 상대경로)
        // 클래스패스 기준 경로로 지정 (src/main/resources/csv/sample.csv)
        String filePath = "csv/sample.csv";

        // CSV 파일 읽기
        CsvReader reader = new CsvReader();

        /*
        List<String> lines = reader.readCsv(filePath);

        // CSV 계산 및 결과 출력
        CsvCalculator calculator = new CsvCalculator();
        calculator.processLines(lines);
        */

        // try-with-resources를 사용하여 스트림을 안전하게 닫습니다.
        try (Stream<String> linesStream = reader.readCsvStream(filePath)) {
            // 스트림을 List로 변환하여 처리합니다.
//            List<String> lines = linesStream.collect(Collectors.toList());

            // 이후 CsvCalculator 등에서 List<String> 기반으로 처리할 수 있습니다.
            CsvCalculator calculator = new CsvCalculator();
//            calculator.processLines(lines);
            calculator.processLines(linesStream);
        }
    }
}
