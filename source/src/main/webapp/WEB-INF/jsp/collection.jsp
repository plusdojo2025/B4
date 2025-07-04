<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="<c:url value='/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/css/collection.css' />">
<meta charset="UTF-8">
<title>コレクション｜生徒用</title>
</head>
<body id="top">

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
 
<!-- ステータス一覧 -->

<section class="status-section">
<h2>ゲットしたステータス</h2>

 <!-- <ul>

    <li>４月〇日　○○○○</li> 
    <li>４月×日　××××</li>
    <li>４月△日　△△△△</li>
    
  </ul> -->
  
  <table border="1">
  <tr>
    <th>ステータス名</th>
  <!--  <th>獲得日時</th> -->
  </tr>

  <c:forEach var="coll" items="${statusList}">
    <tr>
      <td>${coll.statusName}</td>
     <!--  <td>${coll.statusCreatedAtStr}</td>-->
    </tr>
  </c:forEach>
</table>
</section>

 
 
 
<!-- トロフィーをロッカーに表示 -->
<section class="locker-container">

  <h2>ゲットしたトロフィー</h2>
  
  <div class="locker-grid">
  
  <c:forEach var="coll" items="${trophyList}">
  <div class="locker">
       <img src="<c:url value='/img/${coll.trophyPhoto}.png'/>" alt="トロフィー画像">
  </div>
  
 </c:forEach>
  <!-- 空マスで9個まで補完 -->
  <c:forEach begin="1" end="${fn:length(trophyList) < 9 ? 9 - fn:length(trophyList) : 0}">
  <div class="locker"></div>
</c:forEach>
  
 
</div>
</section>


 <!--    <table border="2">
  <tr>
    <th>トロフィー</th>
    <th>獲得日時</th>
  </tr>

  <c:forEach var="coll" items="${trophyList}">
    <tr>
      <td> <img src="/B4/img/${coll.trophyPhoto}" alt="トロフィー画像"></td>
      <td>${coll.trophyCreatedat}</td>
    </tr>
  </c:forEach>
</table>
   -->
 

</main>
</div> 
 
  <!-- メイン（ここまで） -->

  <!-- フッター（ここから） -->
 <footer class="footer">
 <p class="copyright">&copy;-LEGACY-</p>
 </footer>

</body>
</html> 