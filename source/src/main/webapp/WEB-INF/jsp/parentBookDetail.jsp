<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>本の詳細</title>
</head>
<body>

<h2>${book.title}</h2>

<p>著者：${book.author}</p>
<p>出版社：${book.publisher}</p>
<p>ジャンル：${book.genre_Name}</p>
<p>ページ数：${book.page}</p>
<p>場所：${book.gets}</p>
<img src="${pageContext.request.contextPath}/img/${book.cover}" alt="表紙画像" width="200">

<!-- 状態によって表示するボタンを変える -->
<c:choose>

    <c:when test="${statusId == 0}">
        <%-- 「この本を読む」ボタン --%>
        <form action="BookFinishServlet" method="post">
            <input type="hidden" name="bookId" value="${book.id}" />
            <input type="submit" value="この本を読む" />
        </form>
    </c:when>

    <c:when test="${statusId == 1}">
        <%-- 読書中表示 --%>
        <p>読んでいます！</p>
    </c:when>

    <c:when test="${statusId == 2}">
        <c:choose>

            <c:when test="${alreadyRecommended}">
                <%-- すでにおすすめ済み --%>
                <p>おすすめ済みです。</p>
            </c:when>

            <c:otherwise>
                <%-- おすすめ投稿フォーム --%>
                <form action="BookRecommendServlet" method="post">
                    <input type="hidden" name="bookId" value="${book.id}" />
                    <label for="comment">おすすめコメント：</label><br>
                    <textarea name="comment" id="comment" rows="4" cols="40" required></textarea><br>
                    <input type="submit" value="この本をおすすめする" />
                </form>
            </c:otherwise>

        </c:choose>
    </c:when>

</c:choose>

<form action="${pageContext.request.contextPath}/BookFinishServlet" method="post">
    <input type="hidden" name="bookId" value="1" />
</form>

<!-- 戻るボタン -->
<p><a href="${pageContext.request.contextPath}/BookListServlet">← 一覧に戻る</a></p>

</body>
</html>