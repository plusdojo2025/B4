<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value='/css/techerCommon.css' />">
<link rel="stylesheet" href="<c:url value='/css/teacherBook.css' />">
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
 <li><a href="<c:url value='/TeacherHomeServlet' />">ホーム</a></li>
    <li><a href="<c:url value='/BookListServlet' />">一覧</a></li>
    <li><a href="<c:url value='/RegistServlet' />">登録</a></li>
    <li><a href="<c:url value='/BookRecommendServlet' />">おすすめ</a></li>
    <li><a href="<c:url value='/RankingServlet' />">ランキング</a></li>
<li> <button class="logout-btn" onclick="location.href='<c:url value='/LogoutServlet'/>'">ログアウト</button></li>
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

<table border="1">
  <tr>
    <th>表紙</th>
    <th>タイトル</th>
    <th>著者</th>
    <th>出版社</th>
    <th>ページ数</th>
    <th>ジャンル名</th>
    <th>登録日</th>
    <th>操作</th> <!-- ここを追加 -->
  </tr>

  <c:forEach var="book" items="${bookList}">
    <c:url value="/img/${book.cover}" var="coverUrl" />
    <c:url var="detailUrl" value="/BookDetailServlet">
      <c:param name="bookId" value="${book.id}" />
      <c:param name="title" value="${fn:escapeXml(title)}" />
      <c:param name="genreId" value="${fn:escapeXml(genreId)}" />
      <c:param name="page" value="${currentPage}" />
      <c:param name="lastList" value="BookListServlet" />
    </c:url>
    
    <tr>
      <td>
        <a href="${detailUrl}">
          <img src="${coverUrl}" alt="表紙画像" width="100">
        </a>
      </td>
      <td>${book.title}</td>
      <td>${book.author}</td>
      <td>${book.publisher}</td>
      <td>${book.page}</td>
      <td>${book.genre_Name}</td>
      <td>${book.created_atStr}</td>
      <td>
        <!-- 編集ボタン -->
        <form action="<c:url value='/UpdateBookServlet' />" method="get" style="display:inline;">
          <input type="hidden" name="bookId" value="${book.id}" />
          <input type="submit" value="編集" />
        </form><br>
        <!-- 削除ボタン -->
        <form action="<c:url value='/DeleteBookServlet' />" method="get" style="display:inline;" onsubmit="return confirm('本当に削除しますか？');">
          <input type="hidden" name="bookId" value="${book.id}" />
          <input type="submit" value="削除" />
        </form>
      </td>
    </tr>
  </c:forEach>
</table>

 
 
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