<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム | 保護者ページ</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.9.1/dist/chart.min.js"></script>
</head>
<body>
<h1>よも～にんぐ</h1>
<hr>
<p><a href="/B4/parentHomeServlet">ホーム</a></p>
<p><a href="/B4/BookListServlet">一覧</a></p>
<p><a href="/B4/BookRecommendServlet">おすすめ</a></p>
<p><a href="/B4/LogoutServlet">ログアウト</a></p>

<h2>6月17日</h2>

<c:forEach var="pro" items="${progressList}" >
<p>目標${pro.target_page}ページ</p>
<p>進捗${pro.read_page}ページ</p>

</c:forEach>

<h2>成績表</h2>
<c:forEach var="pro" items="${progressList}">
	<input type="hidden" name="id" value="${pro.id}">
	<p>${pro.month}月の成績表</p>
	<p>${pro.target_page}</p>
	<p>${pro.read_page}</p>

</c:forEach>
   	
</body>
</html>