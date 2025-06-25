<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>きろく | 生徒ページ</title>
</head>
<body>
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

<c:if test="${not empty currentBook}">
  <c:url value="/img/${currentBook.cover}" var="coverUrl" />
  <div style="display: flex; align-items: center; margin-bottom: 30px;">
    
    <!-- 表紙画像をリンク化して BookDetailServlet へ -->
    <a href="${pageContext.request.contextPath}/BookDetailServlet?bookId=${currentBook.book_id}&lastList=RecordServlet">
    	<img src="${coverUrl}" alt="表紙画像" width="150" style="margin-right: 20px;">
    </a>

    <div>
      <p>今読んでいる本は「${currentBook.title}」</p>
      <c:choose>
        <c:when test="${not empty todayProgress}">
          <p>${todayProgress.month}月${todayProgress.day}日</p>
          <p>もくひょう：${todayProgress.target_page}ページ</p>
          <p>よんだページ：${todayProgress.read_page}ページ</p>
        </c:when>
        <c:otherwise>
          <p style="color: red; font-weight: bold;">きょうはまだよんでないよ！がんばってね！</p>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
</c:if>

<h2>今までに読んだ本</h2>

<div style="display: flex; flex-wrap: wrap; gap: 20px;">
  <c:forEach var="finbook" items="${finishBookSelectNewList}">
    <c:url value="/img/${finbook.cover}" var="coverUrl" />
    
    <!-- 表紙画像をリンク化 -->
    <a href="${pageContext.request.contextPath}/BookDetailServlet?bookId=${finbook.book_id}&lastList=RecordServlet">
    
      <img src="${coverUrl}" alt="表紙画像" width="150"><br>
      <span class="book-title">${finbook.title}</span>
    </a>
  </c:forEach>
</div>


<!--  
<style>
.container {
    width: 1000px;
    margin: 200px auto;
}
.card {
    width: 400px;
    border: 2px solid #333;
    border-radius: 4px;
    padding: 30px;
    list-style-position: inside;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: white;
    transition-duration: 1s;
    z-index: 2;
}
#mask {
    position: fixed;
    top: 0;
    left: 0;
    bottom: 0;
    right: 0;
    background-color: rgba(0, 0, 0, 0.3);
    transition-duration: 1s;
    z-index: 1;
}
.hidden {
    display: none;
}
</style>

<script>
const card = document.querySelector('.card');
const open = document.getElementById('open');
const close = document.getElementById('close');
const mask = document.getElementById('mask');

open.addEventListener('click', () => {
  card.classList.toggle('hidden');
  mask.classList.toggle('hidden');
  open.classList.toggle('hidden');
});

close.addEventListener('click', () => {
  card.classList.toggle('hidden');
  mask.classList.toggle('hidden');
  open.classList.toggle('hidden');
});
</script>
-->
</body>
</html>