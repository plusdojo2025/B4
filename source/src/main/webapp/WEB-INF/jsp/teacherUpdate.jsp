<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>本の編集</title>
</head>
<body>

<header>
<span>
  <c:out value="${sessionScope.user.name}" /> さん
</span>
<p><a href="<c:url value='/StudentHomeServlet' />">ホーム</a></p>
<p><a href="<c:url value='/RegistServlet' />">登録</a></p>
<p><a href="<c:url value='/BookListServlet' />">一覧</a></p>
<p><a href="<c:url value='/BookRecommendServlet' />">おすすめ</a></p>
<p><a href="<c:url value='/OpinionServlet' />">目安箱</a></p>
<p><a href="<c:url value='/RankingServlet' />">ランキング</a></p>
<p><a href="<c:url value='/LogoutServlet' />">ログアウト</a></p>
</header>

<h2>本の編集フォーム</h2>

<c:if test="${not empty book.cover}">
  <p>登録されている画像：</p>
  <img src="<c:url value='/img/${book.cover}' />" alt="表紙画像" width="150">
</c:if>

<form method="POST" action="<c:url value='/UpdateBookServlet' />">
  <input type="hidden" name="id" value="${book.id}" />

  <label>タイトル：<input type="text" name="title" value="${book.title}" required></label><br>
  <label>著者：<input type="text" name="author" value="${book.author}" required></label><br>
  <label>出版社：<input type="text" name="publisher" value="${book.publisher}" required></label><br>
  <label>場所：<input type="text" name="gets" value="${book.gets}" required></label><br>
  <label>ページ数：<input type="number" name="page" value="${book.page}" required></label><br>

  <label>
    ジャンル：
    <select name="genreId" required>
      <option value="1" <c:if test="${book.genre_id == 1}">selected</c:if>>ファンタジー</option>
      <option value="2" <c:if test="${book.genre_id == 2}">selected</c:if>>ミステリー</option>
      <option value="3" <c:if test="${book.genre_id == 3}">selected</c:if>>バトル</option>
      <option value="4" <c:if test="${book.genre_id == 4}">selected</c:if>>ホラー</option>
      <option value="5" <c:if test="${book.genre_id == 5}">selected</c:if>>コメディ</option>
      <option value="6" <c:if test="${book.genre_id == 6}">selected</c:if>>日常</option>
      <option value="7" <c:if test="${book.genre_id == 7}">selected</c:if>>絵本</option>
      <option value="8" <c:if test="${book.genre_id == 8}">selected</c:if>>昔話</option>
      <option value="9" <c:if test="${book.genre_id == 9}">selected</c:if>>文学</option>
      <option value="10" <c:if test="${book.genre_id == 10}">selected</c:if>>ノンフィクション</option>
      <option value="11" <c:if test="${book.genre_id == 11}">selected</c:if>>エッセイ</option>
      <option value="12" <c:if test="${book.genre_id == 12}">selected</c:if>>学習</option>
      <option value="13" <c:if test="${book.genre_id == 13}">selected</c:if>>図鑑</option>
      <option value="14" <c:if test="${book.genre_id == 14}">selected</c:if>>雑学</option>
      <option value="15" <c:if test="${book.genre_id == 15}">selected</c:if>>その他</option>
    </select>
  </label><br>

  <!-- 表紙画像の変更は今は扱わず。必要であればfile入力を追加 -->
  <input type="hidden" name="cover" value="${book.cover}" />

  <input type="submit" value="更新">
</form>

</body>
</html>
