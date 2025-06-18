<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>本の詳細</title>
</head>
<body>

<h2>${book.title} の詳細</h2>

<p>著者：${book.author}</p>
<p>出版社：${book.publisher}</p>
<p>ジャンル：${book.genre_Name}</p>
<p>ページ数：${book.page}</p>
<p>手に入れた場所：${book.gets}</p>
<p>登録日：${book.created_atStr}</p>
<img src="${pageContext.request.contextPath}/img/${book.cover}" alt="表紙画像" width="200">

<!-- 「この本を読む」ボタン -->
<form action="${pageContext.request.contextPath}/BookDetailServlet" method="post">
  <input type="hidden" name="bookId" value="${book.id}">
  <input type="submit" value="この本を読む">
</form>


<!-- 戻るボタン -->
<p><a href="${pageContext.request.contextPath}/BookListServlet">← 一覧に戻る</a></p>

</body>
</html>
