<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/studentBook.css' />">
  <meta charset="UTF-8">
  <title>本の一覧</title>
</head>
<body>

<div class="page-frame">
<!-- ヘッダー　-->
<header class="header">
<span>
  <c:out value="${sessionScope.user.name}" /> さん
</span>
<div class="logo">よも～にんぐ</div>
<nav class="nav">
<ul>
 <li><a href="<c:url value='/StudentHomeServlet' />">ホーム</a></li>
    <li><a href="<c:url value='/BookListServlet' />">ほんだな</a></li>
    <li><a href="<c:url value='/BookRecommendServlet' />">おすすめ</a></li>
    <li><a href="<c:url value='/ProgressServlet' />">きろく</a></li>
    <li><a href="<c:url value='/RecordServlet' />">せいせき</a></li>
    <li><a href="<c:url value='/CollectionServlet' />">コレクション</a></li>
<li><button class="logout-btn" onclick="location.href="<c:url value='/LogoutServlet'/>">ログアウト</li>
</ul>
</nav>
</header>
<!-- ヘッダー　-->

<main class="main-content"> 
<div class="search-form-container">
<form action="BookListServlet" method="get">
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
</div>


<!--1行に5冊表示 -->
<c:set var="rowSize" value="5" />

<!-- 全体の本数を取得 -->
<c:set var="totalBooks" value="${fn:length(bookList)}" />

<!-- 行の数だけループ -->
<c:forEach var="row" begin="0" end="${(totalBooks - 1) / rowSize}" varStatus="status">

  <div class="book-list">
    <!-- 1行分の本を表示 -->
    <c:forEach var="col" begin="0" end="${rowSize - 1}">
      <c:set var="index" value="${row * rowSize + col}" />
      <c:if test="${index < totalBooks}">
        <c:set var="book" value="${bookList[index]}" />
        <c:url value="/img/${book.cover}" var="coverUrl" />
        <div class="book-item">
          <a href="${pageContext.request.contextPath}/BookDetailServlet?bookId=${book.id}&title=${fn:escapeXml(param.title)}&genreId=${param.genreId}&page=${currentPage}&lastList=BookListServlet">
            <img src="${coverUrl}" alt="表紙画像" width="150"><br>
            <span class="book-title">${book.title}</span>
          </a>
        </div>
      </c:if>
    </c:forEach>
  </div>

  <!-- 1行目と2行目の間に線を入れる -->
  <c:if test="${status.index == 0}">
    <hr class="book-separator">
  </c:if>
</c:forEach>

 <hr class="book-separator">
 
 <div class="pagenation">
<!-- 最初へ -->
<c:if test="${currentPage > 1}">
  <a href="${pageContext.request.contextPath}/BookListServlet?page=1&title=${fn:escapeXml(title)}&genreId=${fn:escapeXml(genreId)}">最初へ</a>
</c:if>
<!-- 前へ -->
<c:if test="${currentPage > 1}">
  <a href="${pageContext.request.contextPath}/BookListServlet?page=${currentPage - 1}&title=${fn:escapeXml(title)}&genreId=${fn:escapeXml(genreId)}">前へ</a>
</c:if>

<!-- ページをプルダウンで選択 -->
<form action="${pageContext.request.contextPath}/BookListServlet" method="get">
 
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
  <a href="${pageContext.request.contextPath}/BookListServlet?page=${currentPage + 1}&title=${fn:escapeXml(title)}&genreId=${fn:escapeXml(genreId)}">次へ</a>
</c:if>
<!-- 最後へ -->
<c:if test="${currentPage < totalPages}">
  <a href="${pageContext.request.contextPath}/BookListServlet?page=${totalPages}&title=${fn:escapeXml(title)}&genreId=${fn:escapeXml(genreId)}">最後へ</a>
</c:if>
</div>
</main>
</div>

 <!-- メイン（ここまで） -->

  <!-- フッター（ここから） -->
 <footer class="footer">
 <p class="copyright">&copy;-LEGACY-</p>
 </footer>

</body>
</html>