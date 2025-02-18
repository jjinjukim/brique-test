<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employee List</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
            text-align: left;
        }
        tr:nth-child(even){background-color: #f9f9f9;}
    </style>
</head>
<body>
<h2>Employee List</h2>
<table>
    <thead>
    <tr>
        <th>Employee No</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Gender</th>
        <th>Hire Date</th>
        <th>Dept Name</th>
        <th>Title</th>
        <th>Max Salary</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="employee" items="${employeeList}">
        <tr>
            <td>${employee.empNo}</td>
            <td>${employee.firstName}</td>
            <td>${employee.lastName}</td>
            <td>${employee.gender}</td>
            <td>${employee.hireDate}</td>
            <td>${employee.deptName}</td>
            <td>${employee.title}</td>
            <td>${employee.maxSalary}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>