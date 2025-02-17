package brique.brique_test.assignment1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Description : CsvCalculator
 * Date : 2/17/25
 * History :
 * - 작성자 : j, 날짜 :2/17/25, 설명 : 최초작성
 *
 * @author j
 * @version 1.0
 */
public class CsvCalculator {
    /**
     * CSV 파일의 각 라인을 처리합니다.
     * 숫자로 구성된 라인은 계산을 수행하여 결과를 출력하고,
     * 숫자가 아닌 값이 하나라도 포함된 라인은 errorLines에 추가합니다.
     *
     * @param lines CSV 파일에서 읽어온 각 라인 목록
     */
    public void processLines(List<String> lines) {
        int totalLines = 0;
        int calculatedLines = 0;
        List<String> errorLines = new ArrayList<>();

        for (String line : lines) {
            totalLines++;
            String[] tokens = line.split(","); // 쉼표 구분
            boolean allNumeric = true;
            double[] nums = new double[tokens.length];
            List<String> nonNumericTokens = new ArrayList<>();

            // 각 토큰이 숫자인지 체크
            for (int i = 0; i < tokens.length; i++) {
                String token = tokens[i].trim();
                try {
                    nums[i] = Double.parseDouble(token);
                } catch (NumberFormatException e) {
                    allNumeric = false;
                    nonNumericTokens.add(token);
                    break;
                }
            }

            if (allNumeric) {
                calculatedLines++;
                double min = Arrays.stream(nums).min().getAsDouble();
                double max = Arrays.stream(nums).max().getAsDouble();
                double sum = Arrays.stream(nums).sum();
                double average = sum / nums.length;
                double stdDev = calculateStdDev(nums, average);
                double median = calculateMedian(nums);

                // 계산 결과를 출력 (형식: 최소값 최대값 합계 평균 표준편차 중간값)
                System.out.printf("%.1f %.1f %.1f %.1f %.14f %.1f%n",
                        min, max, sum, average, stdDev, median);
            } else {
                errorLines.add(line);
            }
        }

        // 최종 결과 출력
        System.out.println("---------------------------------------------");
        System.out.println("The total number of lines: " + totalLines);
        System.out.println("The calculated lines: " + calculatedLines);
        System.out.println("Error lines (non-numeric values):");
        errorLines.forEach(System.out::println);
    }

    /**
     * CSV 파일의 각 라인을 받아 처리합니다.
     * 숫자로 구성된 행은 계산을 수행하여 결과를 출력하고,
     * 숫자가 아닌 값이 하나라도 포함된 행은 계산하지 않고, 해당 행에서 숫자가 아닌 값만 추출하여
     * 나중에 "Error lines (non-numeric values):" 아래에 출력합니다.
     *
     * @param lines CSV 파일의 각 라인을 담은 Stream<String>
     */
    public void processLines(Stream<String> lines) {
        final int[] totalLines = {0};
        final int[] calculatedLines = {0};
        List<String> errorTokens = new ArrayList<>();

        lines.forEach(line -> {
            totalLines[0]++;
            String[] tokens = line.split(",");
            boolean allNumeric = true;
            double[] nums = new double[tokens.length];
            List<String> nonNumericTokens = new ArrayList<>();

            // 각 토큰이 숫자인지 확인
            for (int i = 0; i < tokens.length; i++) {
                String token = tokens[i].trim();
                try {
                    nums[i] = Double.parseDouble(token);
                } catch (NumberFormatException e) {
                    allNumeric = false;
                    // 해당 토큰(숫자가 아닌 값)을 개별적으로 누적
                    errorTokens.add(token);
                }
            }

            if (allNumeric) {
                calculatedLines[0]++;
                double min = Arrays.stream(nums).min().getAsDouble();
                double max = Arrays.stream(nums).max().getAsDouble();
                double sum = Arrays.stream(nums).sum();
                double average = sum / nums.length;
                double stdDev = calculateStdDev(nums, average);
                double median = calculateMedian(nums);

                // 계산 결과 출력 (최소값, 최대값, 합계, 평균, 표준편차, 중간값)
                System.out.printf("%.1f %.1f %.1f %.1f %.14f %.1f%n",
                        min, max, sum, average, stdDev, median);
            }
        });

        // 최종 요약 및 오류 행 출력
        System.out.println("---------------------------------------------");
        System.out.println("The total number of lines: " + totalLines[0]);
        System.out.println("The calculated lines: " + calculatedLines[0]);
        System.out.println("The error values: " + errorTokens);
    }

    /**
     * 표준편차(모집단 기준)를 계산합니다.
     */
    private double calculateStdDev(double[] nums, double average) {
        double sumSq = 0;
        for (double num : nums) {
            sumSq += Math.pow(num - average, 2);
        }
        return Math.sqrt(sumSq / nums.length);
    }

    /**
     * 중간값(메디안)을 계산합니다.
     */
    private double calculateMedian(double[] nums) {
        double[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);
        int n = copy.length;
        if (n % 2 == 1) {
            return copy[n / 2];
        } else {
            return (copy[n / 2 - 1] + copy[n / 2]) / 2.0;
        }
    }
}
