<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>せいせき | 生徒ページ</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.9.1/dist/chart.min.js"></script>
</head>
<body>
<h1>よも～にんぐ</h1>
<hr>
<p><a href="/B4/CollectionServlet">コレクション</a></p>
<p><a href="/B4/studentHomeServlet">ホーム</a></p>
<p><a href="/B4/BookListServlet">ほんだな</a></p>
<p><a href="/B4/BookRecommendServlet">おすすめ</a></p>
<p><a href="/B4/RecordServlet">きろく</a></p>
<p><a href="/B4/ProgressServlet">せいせき</a></p>
<p><a href="/B4/LogoutServlet">ログアウト</a></p>

<form id ="form" method="POST" action="/B4/ProgressServlet">
<select id="select_month" name="month">
<c:forEach  begin="1" end="12" step="1" var="i">
	<option><c:out value="${i}" /></option>  
	</c:forEach>
</select>
</form>

<c:forEach var="pro" items="${progressList}">
	<input type="hidden" name="id" value="${pro.id}">
	<p>${pro.month}月の成績表</p>
	<h3>過去の読書記録</h3>
	<p>${pro.target_page}</p>
	<p>${pro.read_page}</p>

</c:forEach>

<h3>プロフィール</h3>

<h3>好きな○○</h3>

<script>
document.getElementById('form_month').select.onchange = function() {
	location.href = document.getElementById('form_month').select.value;
}
</script>
</body>
</html>