<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/studentHome.css' />">
<meta charset="UTF-8">
<title>ホーム | 生徒ページ</title>
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
  <ul >
    <li><a href="<c:url value='/StudentHomeServlet' />">ホーム</a></li>
    <li><a href="<c:url value='/BookListServlet' />">ほんだな</a></li>
    <li><a href="<c:url value='/BookRecommendServlet' />">おすすめ</a></li>
    <li><a href="<c:url value='/RecordServlet' />">きろく</a></li>
    <li><a href="<c:url value='/ProgressServlet' />">せいせき</a></li>
    <li><a href="<c:url value='/CollectionServlet' />">コレクション</a></li>
     <li> <button class="logout-btn" onclick="location.href='<c:url value='/LogoutServlet'/>'">ログアウト</button></li>
  </ul>
  </nav>
 </header>
 <!-- ヘッダー　-->
 <main class="main-content"> 
<div class="book-detail-box">

<!--  <form id="opinion_form" method="POST" action="/B4/OpinionServlet"> -->
<!--  <input type="submit" name="submit" value="目安箱">-->
<!--  </form> -->

<h2><fmt:formatDate value="${today}" pattern="yyyy年M月d日" /></h2>

<c:forEach var="finbook" items="${finishBookNewList}">
    <c:url value="/img/${finbook.cover}" var="coverUrl" />
        <img src="${coverUrl}" alt="表紙画像" width="150"><br>
</c:forEach>

<form id="goal_form" method="POST" action="<c:url value='/StudentHomeServlet'/>">
  <c:choose>
  <c:when test="${isAllDone}">
    <p style="color: green; font-weight: bold;">きょうはよみおわったよ！</p>
  </c:when>
  <c:otherwise>
    <c:if test="${not empty errorMessage}">
      <p style="color: red; font-weight: bold;">${errorMessage}</p>
    </c:if>
    <c:choose>
      <c:when test="${step == 'record'}">
        <form method="post" action="<c:url value='/StudentHomeServlet' />">
          <label>読んだページ：</label>
          <input type="number" name="read_page" value="0" min="1" max="30" required><br>
          <input type="submit" value="きろくする">
        </form>
      </c:when>
      <c:otherwise>
        <form method="post" action="<c:url value='/StudentHomeServlet' />">
          <label>目標ページ：</label>
          <input type="number" name="target_page" value="0" min="1" max="30" required><br>
          <input type="submit" value="もくひょうをきめる">
        </form>
      </c:otherwise>
    </c:choose>
  </c:otherwise>
</c:choose>
</form>
<c:if test="${not empty errorMessage}">
  <p style="color: red; font-weight: bold;">${errorMessage}</p>
</c:if>


<div class="ranking-section">
<h2>${title}</h2>

<table class="ranking-table" border="1">
  <thead>
    <tr>
      <th>順位</th>
      <th>名前</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="rank" items="${RankList}" varStatus="status">
      <tr>
        <td>${status.index + 1}</td>
        <td>${rank.name}</td>
      </tr>
    </c:forEach>

    <c:if test="${empty RankList}">
      <tr><td colspan="3">ランキングデータがありません。</td></tr>
    </c:if>
  </tbody>
</table>
</div>

<h2>今日のおすすめ</h2>

<c:if test="${not empty todayRecommendation}">
  <c:url value="/img/${todayRecommendation.cover}" var="recommendCoverUrl" />
  <div style="text-align: center; margin: 20px;">
    <img src="${recommendCoverUrl}" alt="おすすめ本の表紙" width="150"><br>
    <span style="display: inline-block; max-width: 120px;">${todayRecommendation.title}</span>
  </div>
</c:if>

<c:if test="${empty todayRecommendation}">
  <p>今日のおすすめ本は見つかりませんでした。</p>
</c:if>
</div>

</main>

 <!-- メイン（ここまで） -->

  <!-- フッター（ここから） -->
 <footer class="footer">
 <p class="copyright">&copy;-LEGACY-</p>
 </footer>

</div>
</body>
</html>