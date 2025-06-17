<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>せいせき | 生徒ページ</title>
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

<h2>6月のせいせき</h2>

<h3>いままでのきろく</h3>

<c:forEach var="pro" items="${progressList}">
    <%-- <form method="POST" action="/webapp1/UpdateDeleteServlet" data-name="${e.name}"> --%>
	<input type="hidden" name="number" value="${pro.id}">
	<p class="item" id="p1">${pro.user_id}</p>
	<p class="item" id="p4">${pro.month}</p>
	<p class="item" id="p3">${pro.target_page}</p>
	<p class="item" id="p4">${pro.read_page}</p>

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