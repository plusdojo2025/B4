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
            <th>順位</th>
            <th>ユーザーID</th>
            <th>ランキング名</th>
            <th>タイプ</th>
            <th>ジャンルID</th>
            <th>期間</th>
        </tr>
        <%
    List<Ranking> RankList = (List<Ranking>) request.getAttribute("RankList");
    if (RankList != null) {
        for (Ranking r : RankList) {
%>
        <tr>
            <td><%= r.getRank_value() %></td>
            <td><%= r.getUser_id() %></td>
            <td><%= r.getName() %></td>
            <td><%= r.getType() %></td>
            <td><%= r.getGenre_id() %></td>
            <td><%= r.getTerm() %></td>
        </tr>
<%
        }
    } else {
%>
        <tr><td colspan="6">ランキングデータが存在しません。</td></tr>
<%
    }
%>
    </table>
</body>
</html>