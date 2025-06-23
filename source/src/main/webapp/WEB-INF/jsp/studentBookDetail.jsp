<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
<p>おすすめされた人数：${book.recommendCount} 人</p>

<c:url value="/img/${book.cover}" var="coverUrl" />
<img src="${coverUrl}" alt="表紙画像" width="200">






<c:if test="${book.id != selectLatestReadingBookId}">
  <form action="BookFinishServlet" method="post">
    <input type="hidden" name="bookId" value="${book.id}" />
    <input type="submit" value="この本を読む" />
  </form>
</c:if>

<c:if test="${book.id == latestReadingBookId}">
  <p>読んでいます！</p>
</c:if>



<!-- 状態によって表示するボタンを変える -->
<c:choose>
    <c:when test="${typeId == 2}">
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

<c:choose>
  <c:when test="${sessionScope.lastList == 'BookRecommendServlet'}">
    <a href="${pageContext.request.contextPath}/BookRecommendServlet?bookId=${book.id}&title=${fn:escapeXml(title)}&genreId=${fn:escapeXml(genreId)}&page=${currentPage}&lastList=BookRecommendServlet">    
      ← おすすめ順一覧に戻る
    </a>
  </c:when>
  <c:otherwise>
    <a href="${pageContext.request.contextPath}/BookListServlet?title=${fn:escapeXml(sessionScope.title)}&genreId=${fn:escapeXml(sessionScope.genreId)}&page=${sessionScope.currentPage}">
      ← 新着順一覧に戻る
    </a>
  </c:otherwise>
</c:choose>

<script>
  function hideButton() {
    document.getElementById("read").style.display = "none";
  }
</script>

</body>
</html>
