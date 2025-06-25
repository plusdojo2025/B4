<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/parentBook.css' />">
<link rel="stylesheet" href="<c:url value='/css/bookDetail.css' />">
  <meta charset="UTF-8">
  <title>本の詳細</title>
</head>
<body>

<!-- オーバーレイ詳細パネル -->
<div class="overlay">
  <div class="detail-panel">

<h2>${book.title}</h2>

<p>著者：${book.author}</p>
<p>出版社：${book.publisher}</p>
<p>ジャンル：${book.genre_Name}</p>
<p>ページ数：${book.page}</p>
<p>場所：${book.gets}</p>
<p>おすすめされた人数：${book.recommendCount} 人</p>

<c:url value="/img/${book.cover}" var="coverUrl" />
<img src="${coverUrl}" alt="表紙画像" width="200">

<!-- 読了済みの本：おすすめ表示のみ -->
  <c:choose>
    <c:when test="${alreadyRecommended}">
      <p>おすすめ済みです。</p>
    </c:when>
    <c:otherwise>
      <form action="BookRecommendServlet" method="post">
        <input type="hidden" name="bookId" value="${book.id}" />
        <label for="comment">おすすめコメント：</label><br>
        <textarea name="comment" rows="4" cols="40" required></textarea><br>
        <input type="submit" value="この本をおすすめする" />
      </form>
    </c:otherwise>
  </c:choose>


<div class="back-button-wrapper">
<div class=back-button>
<c:choose>
  <c:when test="${sessionScope.lastList == 'BookRecommendServlet'}">
    <a href="${pageContext.request.contextPath}/BookRecommendServlet?title=${fn:escapeXml(sessionScope.title)}&genreId=${fn:escapeXml(sessionScope.genreId)}&page=${sessionScope.currentPage}">
      ← おすすめ順一覧に戻る
    </a>
  </c:when>
  <c:otherwise>
    <a href="${pageContext.request.contextPath}/BookListServlet?title=${fn:escapeXml(sessionScope.title)}&genreId=${fn:escapeXml(sessionScope.genreId)}&page=${sessionScope.currentPage}">
      ← 新着順一覧に戻る
    </a>
  </c:otherwise>
</c:choose>

</div>
</div>
</div>
</div>


<script>
  function hideButton() {
    document.getElementById("read").style.display = "none";
  }
</script>

</body>
</html>
