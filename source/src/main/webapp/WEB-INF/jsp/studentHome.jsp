<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<p><a href="/B4/StudentHomeServlet">ホーム</a></p>
<p><a href="/B4/BookListServlet">ほんだな</a></p>
<p><a href="/B4/BookRecommendServlet">おすすめ</a></p>
<p><a href="/B4/RecordServlet">きろく</a></p>
<p><a href="/B4/ProgressServlet">せいせき</a></p>
<p><a href="/B4/LogoutServlet">ログアウト</a></p>


<h2>2025年6月23日</h2>

<form id="opinion_form" method="POST" action="/B4/OpinionServlet">
<input type="submit" name="submit" value="目安箱">
</form>

<c:forEach var="finbook" items="${finishBookList}">
    <c:url value="/img/${finbook.cover}" var="coverUrl" />
    <div style="display: inline-block; margin: 10px; text-align: center;"><!-- 一時的なCSS -->
        <img src="${coverUrl}" alt="表紙画像" width="150"><br>
        <span style="display: inline-block; max-width: 120px;">${finbook.title}</span>
    </div>
  </c:forEach>

<form id="goal_form" method="POST" action="/B4/StudentHomeServlet">

もくひょう<input type="text" name="target_page" value="0"><br>
きろく<input type="text" name="read_page" value="0"><br>
<input type="submit" name="submit" value="OK"><br>
</form>

<h2>ランキング</h2>

<h2>今日のおすすめ</h2>

<h2>今日の先生の本</h2>

</body>
</html>