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

<form method="POST" action="/B4/TeacherHomeServlet">
<c:forEach var="pro" items="${progressList}" >
	<input type="hidden" name="id" value="${pro.id}">
	<p>名前${pro.user_id}</p>
	<p>読んだ本${pro.user_id}</p>
	<p>目標ページ数${pro.target_page}</p>
	<p>読んだページ数${pro.read_page}</p>
</c:forEach>
</form>

<h3>過去の読書記録</h3>

<h3>プロフィール</h3>


<h3>読書傾向</h3>


</body>
</html>