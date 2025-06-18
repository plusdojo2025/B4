<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>成績表 | 管理者ページ</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<h1>よも～にんぐ</h1>
<hr>
<p><a href="/B4/studentHomeServlet">ホーム</a></p>
<p><a href="/B4/UpdateDeleteServlet">登録</a></p>
<p><a href="/B4/BookListServlet">一覧</a></p>
<p><a href="/B4/BookRecommendServlet">おすすめ</a></p>
<p><a href="/B4/OpinionServlet">目安箱</a></p>
<p><a href="/B4/RankingServlet">ランキング</a></p>
<p><a href="/B4/LogoutServlet">ログアウト</a></p>



<form id ="form" method="POST" action="/B4/ProgressServlet">

<select id="select_month" name="month">
<c:forEach  begin="1" end="12" step="1" var="i">
	<option><c:out value="${i}" /></option>  
	</c:forEach>
</select>


 <c:forEach var="pro" items="${progressList}">
	<input type="hidden" name="number" value="${pro.id}">
	<p>${pro.user_id}</p>
	<p>${pro.month}月の成績表</p>
	<p>${pro.target_page}</p>
	<p>${pro.read_page}</p>

</c:forEach>
   	
<h3>過去の読書記録</h3>


<h3>プロフィール</h3>


<h3>読書傾向</h3>


先生からのコメント<input type="text" name="comment">
<input type="submit" name="submit" value="送信">
</form>

<script>
document.getElementById('form').select.onchange = function() {
	location.href = document.getElementById('form').select.value;
}
</script>
 
</body>
</html>