package brique.brique_test.assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Description : TCPSyncClient 클래스의 설명을 여기에 작성한다.
 * Date : 2/18/25
 * History :
 * - 작성자 : j, 날짜 :2/18/25, 설명 : 최초작성
 *
 * @author j
 * @version 1.0
 */
public class TCPSyncClient {

    public static final String SERVER_ADDRESS = "localhost";
    public static final int SERVER_PORT = 5678;
    public static final int CLIENT_PORT = 56768; // 하나의 포트 사용 (필요 시 자동 할당도 가능)

    public static void main(String[] args) {

        String[] messageList = {"Ping", "Ping", "foobar"};

        try {
            // 클라이언트 소켓 생성
            Socket socket = new Socket();
            socket.bind(new InetSocketAddress("localhost", CLIENT_PORT)); // 고정 포트 사용 시
            socket.connect(new InetSocketAddress(SERVER_ADDRESS, SERVER_PORT));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            for (int i = 0; i < messageList.length; i++) {
                System.out.println("Send(" + (i + 1) + "): " + messageList[i]);
                out.println(messageList[i]);
                // 동기식: 응답을 받을 때까지 readLine()에서 대기
                String response = in.readLine();
                System.out.println("Received(" + (i + 1) + "): " + response);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}