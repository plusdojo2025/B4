<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム｜よも～にんぐ</title>
</head>
<body>
<h1 id ="logo">よも～にんぐ</h1>

<div class="box13">
  <ul id="nav">
    <li><a href="home.html">ホーム</a></li>
    <li><a href="">ほんだな</a></li>
    <li><a href="">おすすめ</a></li>
    <li><a href="">グループ</a></li>
    <li><a href="">きろく</a></li>
    <li><a href="">せいせき</a></li>
     <li><a href="">ログアウト</a></li>
  </ul>
  </div>
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

  <c:forEach var="coll" items="${collectionList}">
    <tr>
      <td>${coll.statusName}</td>
      <td>${coll.createdat}</td>
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
  
  <h2>ランキング</h2>
  <h3>4～9月のアクションランキング</h3>

  <!-- メイン（ここまで） -->
  <!-- フッター（ここから） -->
</body>
</html> 