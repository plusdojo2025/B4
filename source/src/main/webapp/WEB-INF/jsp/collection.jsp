<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/collection.css">
<meta charset="UTF-8">
<title>コレクション｜よも～にんぐ</title>
</head>
<body id="top">
<div class="page-frame">
<!-- ヘッダー　-->
<header class="header">
 <div class="logo">よも～にんぐ</div>
 <nav class="nav">
  <ul >
    <li><a href="home.html">ホーム</a></li>
    <li><a href="">ほんだな</a></li>
    <li><a href="">おすすめ</a></li>
    <li><a href="">きろく</a></li>
    <li><a href="">せいせき</a></li>
     <li><button class="logout-btn" onclick="location.href='logout.html'">ログアウト</button></li>
  </ul>
  </nav>
 </header>
 <!-- ヘッダー　-->
 <main class="main-content">
<h2>ゲットしたステータス</h2>

  <ul>

    <li>４月〇日　○○○○</li> 
    <li>４月×日　××××</li>
    <li>４月△日　△△△△</li>
    
  </ul>
  
  <table border="1">
  <tr>
    <th>ステータス名</th>
    <th>獲得日時</th>
  </tr>

  <c:forEach var="coll" items="${statusList}">
    <tr>
      <td>${coll.statusName}</td>
      <td>${coll.statusCreatedAtStr}</td>
    </tr>
  </c:forEach>
</table>
  
  <!-- <table border="1">
  <tr>
  <th>ステータス名</th>
  <th>獲得日時</th>
  
 
    </table>-->

  <h2>ゲットしたトロフィー</h2>
  <table class="">
    <tr>
      <th> </th>
    </tr>

  </table>
  
   <table border="2">
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
   
  <h2>ランキング</h2>
  <h3>4～9月のアクションランキング</h3>
</main>
  <!-- メイン（ここまで） -->

  <!-- フッター（ここから） -->
 <footer class="footer">
 <p class="copyright">&copy;-LEGACY-</p>
 </footer>
</div>
</body>
</html> 