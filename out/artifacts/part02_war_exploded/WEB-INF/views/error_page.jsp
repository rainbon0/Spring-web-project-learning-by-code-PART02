<%--
  Created by IntelliJ IDEA.
  User: bongchangyun
  Date: 2022/02/14
  Time: 11:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" import="java.util.*" %>
<html>
<head>
    <title>Error_Page</title>
</head>
<body>
    <h1>Exception Page</h1>
    <h4><c:out value="${stack}"></c:out></h4>
</body>
</html>
