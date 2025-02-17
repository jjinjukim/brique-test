package brique.brique_test.assignment1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
     * 주어진 파일 경로의 CSV 파일을 읽어서 각 라인을 리스트로 반환합니다.
     *
     * @param filePath CSV 파일 경로 (예: "resources/csv/sample.csv")
     * @return CSV 파일의 각 라인이 담긴 List<String>
     */
    public List<String> readCsv(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // 한줄씩 읽어오기
            while ((line = br.readLine()) != null) {
                // 빈 줄은 건너뜁니다.
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] CSV 파일을 찾을수 없습니다. >> " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("[ERROR] CSV 파일 읽기 오류: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[ERROR] 알 수 없는 에러 >> " + e.getMessage());
            e.printStackTrace();
        }
        return lines;
    }


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
