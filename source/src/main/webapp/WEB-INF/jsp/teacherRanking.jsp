<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.Ranking" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>よも～にんぐ</title>
</head>
<body>
<h1 id ="logo">よも～にんぐ</h1>
<ul>
<li><a href="home.html">ホーム</a></li>
    <li><a href="">登録</a></li>
    <li><a href="">一覧</a></li>
    <li><a href="">おすすめ</a></li>
    <li><a href="">目安箱</a></li>
    <li><a href="">ランキング</a></li>
     <li><a href="">ログアウト</a></li>
  </ul>
   <h2>
    ランキング
  </h2>
  <h3>
    6月　クラス　人気な本
  </h3>
  <table border="1">
        <tr>
            
            <th>ユーザーID</th>
            <th>氏名</th>
            <th>読んだページ数</th>
        </tr>
      <c:if test="${not empty RankList}">
  <c:forEach var="r" items="${RankList}">
    <tr>
        <td>${r.user_id}</td>
        <td>${r.name}</td>
        <td>${r.page}</td>
    </tr>
  </c:forEach>
</c:if>
<c:if test="${empty RankList}">
  <tr><td colspan="6">ランキングデータが見つかりません。</td></tr>
</c:if>
    </table>
</body>
</html>