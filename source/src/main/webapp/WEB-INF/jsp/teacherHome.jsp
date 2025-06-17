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
<p><a href="/B4/studentHomeServlet">ホーム</a></p>
<p><a href="/B4/UpdateDeleteServlet">登録</a></p>
<p><a href="/B4/BookListServlet">一覧</a></p>
<p><a href="/B4/BookRecommendServlet">おすすめ</a></p>
<p><a href="/B4/OpinionServlet">目安箱</a></p>
<p><a href="/B4/RankingServlet">ランキング</a></p>
<p><a href="/B4/LogoutServlet">ログアウト</a></p>

<h2>6月の読書情報</h2>

<c:forEach var="e" items="${progressList}" >
	<form method="POST" action="/B4/TeacherHomeServlet">
	<input type="hidden" name="id" value="${e.id}">
	読んだ本<input type="text" name="book_id" value="${e.book_id}"><br>
	名前<input type="text" name="user_id" value="${e.user_id}"><br>
	目標ページ数<input type="text" name="name" value="${e.target_page}"><br>
	読んだページ数<input type="text" name="zipcode" value="${e.read_page}"><br>
	<input type="submit" name="submit" value="更新">
	<input type="submit" name="submit" value="削除"><br>
	</form>
	<hr>
</c:forEach>

<h3>過去の読書記録</h3>

<h3>プロフィール</h3>


<h3>読書傾向</h3>


</body>
</html>