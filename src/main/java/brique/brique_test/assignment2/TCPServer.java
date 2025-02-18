package brique.brique_test.assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description : TCPServer 클래스의 설명을 여기에 작성한다.
 * Date : 2/18/25
 * History :
 * - 작성자 : j, 날짜 :2/18/25, 설명 : 최초작성
 *
 * @author j
 * @version 1.0
 */
public class TCPServer {
    public static final int PORT = 5678;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) { // ServerSocket을 사용하여 클라이언트의 연결 요청을 받고, 연결이 성립되면 Socket 객체를 통해 통신합니다.
            System.out.println("TCP Server is running on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                // System.out.println("Connected by " + clientSocket.getRemoteSocketAddress());
                // 클라이언트의 IP와 지정한 포트를 출력
                InetSocketAddress remoteAddress = (InetSocketAddress) clientSocket.getRemoteSocketAddress();
                System.out.printf("Connected by ('%s', %d)%n",
                        remoteAddress.getAddress().getHostAddress(),
                        remoteAddress.getPort());
                // 클라이언트별로 새로운 스레드를 생성하여 처리
                new Thread(() -> handleClient(clientSocket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            int messageCounter = 0;
            String line;
            while ((line = in.readLine()) != null) {
                messageCounter++;
                final String receivedMsg = line;
                final int currentMsgIndex = messageCounter;
                System.out.println("Received(" + currentMsgIndex + "): " + receivedMsg);
                // 비동기 응답 처리 (3초 후 응답)
                new Thread(() -> {
                    try {
                        Thread.sleep(3000);
                        String response;
                        if ("Ping".equalsIgnoreCase(receivedMsg)) {
                            response = "Pong";
                        /*
                        // 대소문자 구분 없이 "Ping"이면 "Pong", 그렇지 않으면 원본 메시지 그대로 응답
                        } else if ("Ping".equalsIgnoreCase(receivedMsg)){
                            response = "Pong";
                        */
                        } else {
                            response = receivedMsg;
                        }
                        // 응답 전송 (여러 스레드가 동시에 쓰지 않도록 동기화)
                        synchronized (out) {
                            out.println(response);
                        }
                        System.out.println("Send: " + response + " (" + currentMsgIndex + ")");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
