# brique-test

## 개발환경
- SpringBoot-3.4.2
- Java-21.0.6
- Mybatis-3.0.4
- MariaDB-3.4.1

## 문제 확인 가이드
- 원하는 위치에 프로젝트를 다운받아 IDE로 실행시켜줍니다.(InteliJ 권장)
- `gradle build`를 하여 실행가능한 상태로 만들어줍니다.
- README.md 에 적혀있는 가이드에 따라 TEST 해줍니다.

### Assignment1
csv 파일을 읽어서 오류가 있는 항목들을 걸러내고, 필요한 내용이 있는 항목에 대해서 결과를 계산 하여 출력한다.

- 제약 조건
  - 숫자에 해당하는 값만 계산 결과를 출력하고 숫자가 아닌 값이 하나라도 존재하면 해당 행에 대해서는 계산 결과를 출력하지 않는다.
  - 출력 후 숫자가 아닌 값들만 모아서 출력해본다
- 각 라인별로 계산해야 하는 항목
    - 최소값
    - 최대값
    - 합계
    - 평균
    - 표준편차
    - 중간값

- **`Assignment1 테스트 방법`**
  - src/main/java/brique/brique_test/assignment1/Assignment1.java `Assignment1.main()` run 실행
- **`Assignment1 실행 결과`**
  ``` ...
  3.0 84.0 334.0 33.4 27.35031992500270 22.5
  9.0 98.0 579.0 57.9 32.05136502553362 68.5
  24.0 99.0 645.0 64.5 29.01120473196520 70.0
  10.0 88.0 536.0 53.6 22.05538483001373 53.5
  5.0 93.0 637.0 63.7 23.79096467148821 70.5
  3.0 98.0 533.0 53.3 26.59718030167860 58.0
  4.0 97.0 526.0 52.6 33.97410778813772 49.5
  14.0 85.0 363.0 36.3 22.42342525128576 32.0
  11.0 90.0 570.0 57.0 29.86636904613616 62.5
  7.0 92.0 368.0 36.8 22.11696181666913 31.5
  4.0 94.0 323.0 32.3 24.00437460130965 29.5
  ---------------------------------------------
  The total number of lines: 500001
  The calculated lines: 499984
  The error values: [p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, "W", "E", "L", "C", "O", "M", "E", " ", "T", O, " ", "B", "R", "I", Q, "U", "E"]
  ```

### Assignment2
클라이언트 프로그램이 서버 프로그램에 Ping 을 보내면 Pong 을 응답받는 프로그램 작성

- 제약 조건
  - TCP, UDP 중 택일
  - 클라이언트에서 보낸 메시지가 Ping 일 경우에 Pong 응답을 한다.
  - 클라이언트에서 보낸 메시지가 Ping 이 아니면 클라이언트에서 보낸 메시지를 그대로 응답을 한다.
- 가산점
  - 비동기 요청/응답을 수행하도록 프로그램을 작성.
  - client는 요청을 보낸 후 server의 응답을 기다리지 않고 바로 다음 요청을 보낼 수 있다.
  - server는 client로부터 요청을 받은 후, 3초간 기다렸다가 응답을 보낸다.

- **`Assignment2 테스트 방법`**
  - src/main/java/brique/brique_test/assignment2/TCPServer.java 경로의 `TCPServer.main()` 실행 (`*Client` 실행 이전 선행 필요)
    ![Assignment2](https://github.com/user-attachments/assets/2250a708-24eb-4fd8-a5bf-006e2df538de)
  - src/main/java/brique/brique_test/assignment2/* 경로의 `TCPSyncClient.main()` 혹은 `TCPAsyncClient.main()` 실행
    - `TCPAsyncClient` 는 비동기 
    - `TCPSyncClient` 는 동기
    ![Assignment2](https://github.com/user-attachments/assets/93457583-0b7e-4c5b-97b8-ae9949bc031b)

- **`Assignment2 실행 결과`**
  - `TCPServer`
  ```
  > Task :TCPServer.main()
  TCP Server is running on port 56789
  Connected by ('127.0.0.1', 56768)
  Received(1): Ping
  Received(2): Ping
  Send: Pong (1)
  Send: Pong (2)
  Received(3): foobar
  Send: foobar (3)
  ``` 
  - `TCPSyncClient`
  ```
  > Task :TCPSyncClient.main()
  Send(1): Ping
  Received(1): Pong
  Send(2): Ping
  Received(2): Pong
  Send(3): foobar
  Received(3): foobar 
  ```

### Assignment3
다음 조건에 맞는 결과를 출력

- 제약조건
  - 출력 열은 총 8개이다.
    - 종업원 번호 (emp_no)
    - 이름 (first_name)
    - 성 (last_name)
    - 성별 (gender)
    - 고용일자 (hire_date)
    - 부서 이름 (dept_name)
    - 직급 (title)
    - 최대 급여 (max_salary)
  - 2000년 이후 고용된 종업원들을 대상으로 한다
  - 최대 급여는 각 종업원들이 받았던 급여 중 가장 높은 값을 의미한다.
  - 문제풀이의 결과는 SQL query 만이 아닌 프로그램이다.

- **`Assignment3 테스트 방법`**
  1. Test Code
     - src/main/java/brique/brique_test/EmployeeControllerTest.java 경로의 testEmployeeApiList() run 실행
     - info 로그 확인
  2. WEB
     - src/main/java/brique/brique_test/BriqueTestApplication.java 경로의 BriqueTestApplication.main() run 실행
     - http://localhost:8082/employee/api/list 접속
       ![/BriqueTestApplication.java](https://github.com/user-attachments/assets/fd98e7b5-c47a-4d50-a035-3e9470398361)
       
-  **`Assignment3 실행 결과`**
   ![employee](https://github.com/user-attachments/assets/89e03674-b8e1-4f3a-90ed-7e5dd62eced5)
     ```
        EmployeeController: Retrieved 15 employees
        Employee: EmployeeProjection{empNo=47291, firstName='Ulf', lastName='Flexer', gender='M', hireDate=2000-01-12, deptName='Human Resources', title='Staff', maxSalary=52206}
        Employee: EmployeeProjection{empNo=60134, firstName='Seshu', lastName='Rathonyi', gender='F', hireDate=2000-01-02, deptName='Customer Service', title='Staff', maxSalary=61320}
        Employee: EmployeeProjection{empNo=60134, firstName='Seshu', lastName='Rathonyi', gender='F', hireDate=2000-01-02, deptName='Marketing', title='Staff', maxSalary=61320}
        Employee: EmployeeProjection{empNo=72329, firstName='Randi', lastName='Luit', gender='F', hireDate=2000-01-02, deptName='Human Resources', title='Staff', maxSalary=44619}
        Employee: EmployeeProjection{empNo=108201, firstName='Mariangiola', lastName='Boreale', gender='M', hireDate=2000-01-01, deptName='Production', title='Senior Engineer', maxSalary=62286}
        Employee: EmployeeProjection{empNo=205048, firstName='Ennio', lastName='Alblas', gender='F', hireDate=2000-01-06, deptName='Finance', title='Senior Staff', maxSalary=80596}
        Employee: EmployeeProjection{empNo=222965, firstName='Volkmar', lastName='Perko', gender='F', hireDate=2000-01-13, deptName='Human Resources', title='Senior Staff', maxSalary=52953}
        Employee: EmployeeProjection{empNo=226633, firstName='Xuejun', lastName='Benzmuller', gender='F', hireDate=2000-01-04, deptName='Marketing', title='Staff', maxSalary=53036}
        Employee: EmployeeProjection{empNo=226633, firstName='Xuejun', lastName='Benzmuller', gender='F', hireDate=2000-01-04, deptName='Sales', title='Staff', maxSalary=53036}
        Employee: EmployeeProjection{empNo=227544, firstName='Shahab', lastName='Demeyer', gender='M', hireDate=2000-01-08, deptName='Human Resources', title='Senior Staff', maxSalary=43267}
        Employee: EmployeeProjection{empNo=422990, firstName='Jaana', lastName='Verspoor', gender='F', hireDate=2000-01-11, deptName='Development', title='Engineer', maxSalary=40638}
        Employee: EmployeeProjection{empNo=424445, firstName='Jeong', lastName='Boreale', gender='M', hireDate=2000-01-03, deptName='Development', title='Engineer', maxSalary=42176}
        Employee: EmployeeProjection{empNo=428377, firstName='Yucai', lastName='Gerlach', gender='M', hireDate=2000-01-23, deptName='Production', title='Engineer', maxSalary=65075}
        Employee: EmployeeProjection{empNo=463807, firstName='Bikash', lastName='Covnot', gender='M', hireDate=2000-01-28, deptName='Quality Management', title='Engineer', maxSalary=52793}
    ```
### Assignment4
기온과 습도를 입력 받을 수 있도록 입력 테이블을 만들고, 그 입력 값에 대해 선꺽은선 그래프를 구현

- 제약조건
  - Chart libraries를 사용할 수 있다.
  - 입력은 계속 변경 될 수 있으며 그래프에 바로 반영 되어야 한다.

- 가산점
  - 입력 테이블 아래에 ＂random＂ 버튼을 만들고, ＂random＂ 버튼 클릭 시 자동으로 입력 테이블의＂기온＂, ＂습도＂ 항목에 임의의 값이 입력된다.
  - 입력이 변경되면 자동으로 chart에 변경된 값이 반영 된다.

- **`Assignment4 테스트 방법`**
  - WEB
     - src/main/java/brique/brique_test/BriqueTestApplication.java 경로의 BriqueTestApplication.main() run 실행
     - http://localhost:8082/chart/api/graph 접속
       ![/BriqueTestApplication.java](https://github.com/user-attachments/assets/fd98e7b5-c47a-4d50-a035-3e9470398361)
       
- **`Assignment4 실행 결과`**
![chart](https://github.com/user-attachments/assets/93862ca3-da6c-43a2-8dca-2f63164bd6b6)

### Assignment5
호출할 때마다 랜덤한 response 를 주는 서버가 있다. 이서버를 100번 호출하여 각각의 결과 값들이 몇 번 반복 되었는지 계산한다.

- 제약조건
  - 마지막에 각 횟수들의 총 합을 출력한다.
  - 가장 많은 횟수가 나온 값부터 출력한다

- **`Assignment5 테스트 방법`**
-   - src/main/java/brique/brique_test/assignment5/Assignment5.java 경로의 `Assignment5.main()` run 실행

- **`Assignment5 실행 결과`**
  ```
    > Task :Assignment5.main()
    count: 13 {"id":10,"quote":"Really loving Spring Boot, makes stand alone Spring apps easy."}
    count: 12 {"id":11,"quote":"I have two hours today to build an app from scratch. @springboot to the rescue!"}
    count: 11 {"id":4,"quote":"Previous to Spring Boot … and many hours of frustration."}
    count: 9 {"id":12,"quote":"@springboot with @ springframework is pure productivity! … # newFavLib"}
    count: 9 {"id":3,"quote":"Spring has come quite a ways in addressing … I built an application using it."}
    count: 9 {"id":7,"quote":"The real benefit of Boot, however …, I know it's a safe bet."}
    count: 8 {"id":8,"quote":"I don't worry about my code scaling … keeping the conventions that just work."}
    count: 8 {"id":9,"quote":"So easy it is to switch container in #springboot."}
    count: 7 {"id":1,"quote":"Working with Spring Boot is like pair programming with the Spring developers."}
    count: 5 {"id":5,"quote":"Spring Boot solves this problem … just to figure out how it's all pieced together."}
    count: 5 {"id":2,"quote":"With Boot you deploy everywhere you can find a JVM basically."}
    count: 4 {"id":6,"quote":"It embraces convention over configuration … such as Ruby on Rails."}
    Total count: 100
  ```
 ### Assignment7
  괄호가 알맞게 짝지어진 가장 긴 부분의 길이를 구하시오. ‘(‘와 ‘)’로만 이루어진 문자열에서, 괄호
  가 알맞게 짝지어진 가장 긴 부분의 길이를 구한다. “(()”의 경우 가장 긴 유효한 부분은 “()” 이므로
  길이는 2 이다. “)()())”의 경우는, 가장 긴 유효한 부분은 “()()” 이므로 길이는 4 이다.

- **`Assignment7 테스트 방법`**
  - src/main/java/brique/brique_test/assignment5/Assignment5.java 경로의 `Assignment5.main()` 의
    ```
    String s1 = "(()()()()()))";
    String s2 = ")()())";
    ```
    안에 원하는 입력값을 넣어주세요.
  - src/main/java/brique/brique_test/assignment5/Assignment5.java 경로의 `Assignment5.main()` run 실행
  ![Assignment7](https://github.com/user-attachments/assets/6410cf9e-684b-4033-b445-f53da5ee8f6a)

- **`Assignment7 실행 결과`**
  ```
    > Task :Assignment7.main()
    Input: (()()()()())) -> result: 12
    Input: )()()) -> result: 4
  ```

  
