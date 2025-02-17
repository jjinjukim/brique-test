package brique.brique_test.assignment1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

/**
 * Description : CsvReader
 * Date : 2/17/25
 * History :
 * - 작성자 : j, 날짜 :2/17/25, 설명 : 최초작성
 *
 * @author j
 * @version 1.0
 */
public class CsvReader {
    /**
     * 주어진 CSV 파일 경로에서 CSV 데이터를 스트림으로 반환합니다.
     * 클래스패스 기준 경로 (예: "csv/sample.csv")로 접근합니다.
     * 파일의 첫 줄(헤더)은 스킵하고, 빈 줄은 제외합니다.
     *
     * @param filePath CSV 파일 경로 (예: "csv/sample.csv")
     * @return CSV 파일의 각 라인을 담은 Stream<String>
     */
    public Stream<String> readCsvStream(String filePath) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
            if (is == null) {
                System.out.println("[ERROR] CSV 파일을 찾을 수 없습니다. >> " + filePath);
                return Stream.empty();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            return br.lines()
                    .filter(line -> !line.trim().isEmpty())
//                    .skip(1) // 헤더 라인은 건너뜁니다.
            ;
        } catch (Exception e) {
            System.err.println("[ERROR] CSV 파일 읽기 오류: " + e.getMessage());
            e.printStackTrace();
            return Stream.empty();
        }
    }
}
