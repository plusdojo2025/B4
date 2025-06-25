<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム | 管理者ページ</title>
<link rel="stylesheet" href="<c:url value='/css/techerCommon.css' />">
<link rel="stylesheet" href="<c:url value='/css/teacherHome.css' />">
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
<li><button class="logout-btn" onclick="location.href="<c:url value='/LogoutServlet'/>">ログアウト</li>
</ul>
</nav>
</header>
<!-- ヘッダー　-->

<main class="main-content"> 
<select id="select_class">
  <option value="">-- 選択してください --</option>
  <option value="1年1組">1年1組</option>
  <option value="1年2組">1年2組</option>
  <option value="1年3組">1年3組</option>
  <option value="2年1組">2年1組</option>
  <option value="2年2組">2年2組</option>
  <option value="2年3組">2年3組</option>
  <option value="3年1組">3年1組</option>
  <option value="3年2組">3年2組</option>
  <option value="3年3組">3年3組</option>
  <option value="4年1組">4年1組</option>
  <option value="4年2組">4年2組</option>
  <option value="4年3組">4年3組</option>
  <option value="5年1組">5年1組</option>
  <option value="5年2組">5年2組</option>
  <option value="5年3組">5年3組</option>
  <option value="6年1組">6年1組</option>
  <option value="6年2組">6年2組</option>
  <option value="6年3組">6年3組</option>
</select>

<h2 id="output"></h2>

<script src="js/pulldownClass.js"></script>

<h3><%= month %>月<%= day %>日の読書情報</h3>

<form method="POST" action="/B4/TeacherHomeServlet">
<table>
<tr><th>名前</th><th>読んだ本</th><th>目標ページ数</th><th>読んだページ数</th></tr>
<c:forEach var="pro" items="${progressList}" >
	<input type="hidden" name="id" value="${pro.id}">
	<tr><th><a href="/B4/ProgressServlet">${pro.name}</a></th><th>${pro.book_id}</th><th>${pro.target_page}</th><th>${pro.read_page}</th></tr>
</c:forEach>
</table>
</form>

<h3>過去の読書記録</h3>

</main>
</div>

<!-- メイン（ここまで） -->

  <!-- フッター（ここから） -->
 <footer class="footer">
 <p class="copyright">&copy;-LEGACY-</p>
 </footer>
 
</body>
</html>