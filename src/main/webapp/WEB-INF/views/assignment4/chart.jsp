<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Assignment 4: 월별 기온 & 습도 그래프</title>
    <!-- Chart.js CDN -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        .container {
            display: flex;
            flex-direction: row;
            gap: 20px;
        }
        .left-pane {
            flex: 1;
            padding: 10px;
        }
        .right-pane {
            flex: 2;
            padding: 10px;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            margin-bottom: 20px;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        button {
            padding: 8px 16px;
            font-size: 14px;
        }
    </style>
</head>
<body>
<h2>Assignment 4: 월별 기온 및 습도 입력 및 실시간 그래프</h2>
<div class="container">
    <div class="left-pane">
        <h3>입력 테이블</h3>
        <table id="inputTable">
            <thead>
            <tr>
                <th>월</th>
                <th>평균 기온</th>
                <th>평균 습도</th>
            </tr>
            </thead>
            <tbody>
            <%
                String[] months = {"1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"};
                for (int i = 0; i < months.length; i++) {
            %>
            <tr>
                <td><%= months[i] %></td>
                <td><input type="number" step="0.1" class="temperature" value="0"></td>
                <td><input type="number" step="0.1" class="humidity" value="0"></td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <button id="randomButton">Random</button>
    </div>
    <div class="right-pane">
        <h3>실시간 그래프</h3>
        <canvas id="myChart" width="300" height="200"></canvas>
    </div>
</div>
<script src="<c:url value='/chart/chart.js'/>"></script>
</body>
</html>