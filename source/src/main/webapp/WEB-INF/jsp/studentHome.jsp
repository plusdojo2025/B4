<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

<h2>2025年6月23日</h2>

<form id="opinion_form" method="POST" action="/B4/OpinionServlet">
<input type="submit" name="submit" value="目安箱">
</form>

<c:forEach var="finbook" items="${finishBookNewList}">
    <c:url value="/img/${finbook.cover}" var="coverUrl" />
        <img src="${coverUrl}" alt="表紙画像" width="150"><br>
</c:forEach>

<form id="goal_form" method="POST" action="/B4/StudentHomeServlet">


<c:choose>
  <c:when test="${step == 'record'}">
    <form method="post" action="StudentHomeServlet">
      <label>読んだページ：</label>
      <input type="number" name="read_page" value="0"><br>
      <input type="submit" value="きろくする">
    </form>
  </c:when>
  
  <c:otherwise>
    <form method="post" action="StudentHomeServlet">
      <label>目標ページ：</label>
      <input type="number" name="target_page" value="0"><br>
      <input type="submit" value="もくひょうをきめる">
    </form>
  </c:otherwise>
</c:choose>


</form>


<h2>${title}</h2>
<table border="1">
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


<h2>今日の先生の本</h2>



</main>

 <!-- メイン（ここまで） -->

  <!-- フッター（ここから） -->
 <footer class="footer">
 <p class="copyright">&copy;-LEGACY-</p>
 </footer>

</div>
</body>
</html>