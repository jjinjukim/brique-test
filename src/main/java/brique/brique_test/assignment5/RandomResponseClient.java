package brique.brique_test.assignment5;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Description : RandomResponseClient 클래스의 설명을 여기에 작성한다.
 * Date : 2/18/25
 * History :
 * - 작성자 : j, 날짜 :2/18/25, 설명 : 최초작성
 *
 * @author j
 * @version 1.0
 */
public class RandomResponseClient {
    private final HttpClient httpClient;
    private final String url;

    public RandomResponseClient(String url) {
        this.httpClient = HttpClient.newHttpClient();
        this.url = url;
    }

    /**
     * URL에 GET 요청을 보내고 응답 문자열을 반환합니다.
     */
    public String fetchResponse() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body().trim();
    }
}
