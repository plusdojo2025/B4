<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>成績表 | 保護者ページ</title>
</head>
<body>
<h1>よも～にんぐ</h1>
<hr>
<p><a href="/B4/parentHomeServlet">ホーム</a></p>
<p><a href="/B4/BookListServlet">一覧</a></p>
<p><a href="/B4/BookRecommendServlet">おすすめ</a></p>
<p><a href="/B4/LogoutServlet">ログアウト</a></p>



<form id ="form" method="POST" action="/B4/ProgressServlet">
<select id="select_month" name="month">
<c:forEach  begin="1" end="12" step="1" var="i">
	<option><c:out value="${i}" /></option>  
	</c:forEach>
</select>

<h2>6月の成績表</h2>

<h3>過去の読書記録</h3>

<c:forEach var="pro" items="${progressList}">
	<input type="hidden" name="number" value="${pro.id}">
	<p class="item" id="p1">${pro.user_id}</p>
	<p class="item" id="p4">${pro.month}</p>
	<p class="item" id="p3">${pro.target_page}</p>
	<p class="item" id="p4">${pro.read_page}</p>

	</c:forEach>

<h3>プロフィール</h3>

<h3>読書傾向</h3>
</form>

<script>
document.getElementById('form_month').select.onchange = function() {
	location.href = document.getElementById('form_month').select.value;
}
</script>
</body>
</html>