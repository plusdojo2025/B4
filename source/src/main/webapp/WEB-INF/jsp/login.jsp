<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/login.css' />">
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>



<body>
<div class="page-frame">
<!-- ヘッダー　-->
<header class="header">
 <div class="logo">よも～にんぐ</div>
</header>

<h2>ログイン</h2>

<% String errorMessage = (String) request.getAttribute("errorMessage"); %>
<% if (errorMessage != null) { %>
    <p style="color:red;"><%= errorMessage %></p>
<% } %>


<form method="post" action="LoginServlet">
    ユーザー：<input type="text" name="users_id">
    パスワード：<input type="password" name="password"><br>
    <input type="submit" value="ログイン">
</form>

  <!-- フッター（ここから） -->
 <footer class="footer">
 <p class="copyright">&copy;-LEGACY-</p>
 </footer>
</div>
</body>
</html>