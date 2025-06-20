<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム | 管理者ページ</title>
</head>
<body>
<h1>よも～にんぐ</h1>
<hr>
<p><a href="/B4/TeacherHomeServlet">ホーム</a></p>
<p><a href="/B4/UpdateDeleteServlet">登録</a></p>
<p><a href="/B4/BookListServlet">一覧</a></p>
<p><a href="/B4/BookRecommendServlet">おすすめ</a></p>
<p><a href="/B4/OpinionServlet">目安箱</a></p>
<p><a href="/B4/RankingServlet">ランキング</a></p>
<p><a href="/B4/LogoutServlet">ログアウト</a></p>

<h2>6月の読書情報</h2>

<form method="POST" action="/B4/TeacherHomeServlet">
<table>
<tr><th>名前</th><th>読んだ本</th><th>目標ページ数</th><th>読んだページ数</th></tr>
<c:forEach var="pro" items="${progressList}" >
	<input type="hidden" name="id" value="${pro.id}">
	<tr><th><a href="/B4/ProgressServlet">${pro.user_id}</a></th><th>${pro.book_id}</th><th>${pro.target_page}</th><th>${pro.read_page}</th></tr>
</c:forEach>
</table>
</form>

<h3>過去の読書記録</h3>


</body>
</html>