<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>おすすめしてる本</title>
</head>
<body>

<header>
<span>
  <c:out value="${sessionScope.user.name}" /> さん
</span>
<p><a href="<c:url value='/StudentHomeServlet' />">ホーム</a></p>
<p><a href="<c:url value='/BookListServlet' />">一覧</a></p>
<p><a href="<c:url value='/BookRecommendServlet' />">おすすめ</a></p>
<p><a href="<c:url value='/LogoutServlet' />">ログアウト</a></p>
</header>

<h2>おすすめしてる本</h2>

<form action="BookRecommendServlet" method="get">
  題名：<input type="text" name="title" value="${param.title}">
  ジャンル：
  <select name="genreId">
  <option value="">-- 指定なし --</option>
  <option value="1" <c:if test="${param.genreId == '1'}">selected</c:if>>ファンタジー</option>
  <option value="2" <c:if test="${param.genreId == '2'}">selected</c:if>>ミステリー</option>
  <option value="3" <c:if test="${param.genreId == '3'}">selected</c:if>>バトル</option>
  <option value="4" <c:if test="${param.genreId == '4'}">selected</c:if>>ホラー</option>
  <option value="5" <c:if test="${param.genreId == '5'}">selected</c:if>>コメディ</option>
  <option value="6" <c:if test="${param.genreId == '6'}">selected</c:if>>日常</option>
  <option value="7" <c:if test="${param.genreId == '7'}">selected</c:if>>絵本</option>
  <option value="8" <c:if test="${param.genreId == '8'}">selected</c:if>>昔話</option>
  <option value="9" <c:if test="${param.genreId == '9'}">selected</c:if>>文学</option>
  <option value="10" <c:if test="${param.genreId == '10'}">selected</c:if>>ノンフィクション</option>
  <option value="11" <c:if test="${param.genreId == '11'}">selected</c:if>>エッセイ</option>
  <option value="12" <c:if test="${param.genreId == '12'}">selected</c:if>>学習</option>
  <option value="13" <c:if test="${param.genreId == '13'}">selected</c:if>>図鑑</option>
  <option value="14" <c:if test="${param.genreId == '14'}">selected</c:if>>雑学</option>
  <option value="15" <c:if test="${param.genreId == '15'}">selected</c:if>>その他</option>    
  </select>
  <input type="submit" value="検索">
</form>

  <c:forEach var="book" items="${bookList}">
    <c:url value="/img/${book.cover}" var="coverUrl" />
    <div style="display: inline-block; margin: 10px; text-align: center;"><!-- 一時的なCSS -->
		<a href="${pageContext.request.contextPath}/BookDetailServlet?bookId=${book.id}&title=${fn:escapeXml(title)}&genreId=${fn:escapeXml(genreId)}&page=${currentPage}&lastList=BookRecommendServlet">
        <img src="${coverUrl}" alt="表紙画像" width="150"><br>
        <span style="display: inline-block; max-width: 120px;">${book.title}</span>
      </a>
    </div>
  </c:forEach>

<!-- 最初へ -->
<c:if test="${currentPage > 1}">
  <a href="${pageContext.request.contextPath}/BookRecommendServlet?page=1&title=${fn:escapeXml(title)}&genreId=${fn:escapeXml(genreId)}">最初へ</a>
</c:if>
<!-- 前へ -->
<c:if test="${currentPage > 1}">
  <a href="${pageContext.request.contextPath}/BookRecommendServlet?page=${currentPage - 1}&title=${fn:escapeXml(title)}&genreId=${fn:escapeXml(genreId)}">前へ</a>
</c:if>

<!-- ページをプルダウンで選択 -->
<form action="${pageContext.request.contextPath}/BookRecommendServlet" method="get">
  <!-- 検索条件を保持 -->
  <input type="hidden" name="title" value="${fn:escapeXml(title)}" />
  <input type="hidden" name="genreId" value="${fn:escapeXml(genreId)}" />

  <label for="pageSelect">ページ：</label>
  <select id="pageSelect" name="page" onchange="this.form.submit()">
    <c:forEach var="i" begin="1" end="${totalPages}">
      <option value="${i}" <c:if test="${i == currentPage}">selected</c:if>>${i}</option>
    </c:forEach>
  </select>
</form>

<!-- 次へ -->
<c:if test="${currentPage < totalPages}">
  <a href="${pageContext.request.contextPath}/BookRecommendServlet?page=${currentPage + 1}&title=${fn:escapeXml(title)}&genreId=${fn:escapeXml(genreId)}">次へ</a>
</c:if>
<!-- 最後へ -->
<c:if test="${currentPage < totalPages}">
  <a href="${pageContext.request.contextPath}/BookRecommendServlet?page=${totalPages}&title=${fn:escapeXml(title)}&genreId=${fn:escapeXml(genreId)}">最後へ</a>
</c:if>

</body>
</html>