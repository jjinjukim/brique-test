package brique.brique_test.assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Description : TCPAsyncClient 클래스의 설명을 여기에 작성한다.
 * Date : 2/18/25
 * History :
 * - 작성자 : j, 날짜 :2/18/25, 설명 : 최초작성
 *
 * @author j
 * @version 1.0
 */
public class TCPAsyncClient {

    public static final String SERVER_ADDRESS = "localhost";
    public static final int SERVER_PORT = 5678;
    public static final int CLIENT_PORT = 56768; // 하나의 포트 사용 (필요 시 자동 할당도 가능)

    public static void main(String[] args) {

        String[] messageList = {"Ping", "Ping", "foobar"};

        try {
            // 1) 소켓 생성 후, 필요 시 로컬 포트 바인딩
            Socket socket = new Socket();
            socket.bind(new InetSocketAddress("localhost", CLIENT_PORT)); // 고정 포트 사용 시
            socket.connect(new InetSocketAddress(SERVER_ADDRESS, SERVER_PORT));

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 2) 별도의 스레드에서 서버 응답 수신 (비동기)
            Thread responseHandler = new Thread(() -> {
                try {
                    String resp;
                    int count = 0;
                    while ((resp = in.readLine()) != null) {
                        count++;
                        System.out.println("Received: " + resp + " (" + count + ")");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            responseHandler.setDaemon(true);
            responseHandler.start();

            // 3) 클라이언트 메인 스레드는 응답 대기 없이 메시지 연속 전송
            for (int i = 0; i < messageList.length; i++) {
                System.out.println("Send(" + (i + 1) + "): " + messageList[i]);
                out.println(messageList[i]);
                // 메시지 전송 간격 (옵션)
                Thread.sleep(100);
            }

            // 4) 응답을 수신할 충분한 시간 대기 후 종료
            Thread.sleep(5000);
            socket.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
