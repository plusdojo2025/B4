<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム | 生徒ページ</title>
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


<h2>2025年6月16日</h2>

<form id="opinion_form" method="POST" action="/B4/OpinionServlet">
<input type="submit" name="submit" value="目安箱">
</form>

<form id="goal_form" method="POST" action="/B4/OpinionServlet">
もくひょう<input type="text" name="goal_page">
<input type="submit" name="submit" value="OK">
</form>

<form id="read_form" method="POST" action="/B4/OpinionServlet">
きろく<input type="text" name="read_page">
<input type="submit" name="submit" value="OK">
</form>

<h2>ランキング</h2>
        人気の本
        おすすめ
        ジャンル

</body>
</html>