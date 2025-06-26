<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value='/css/techerCommon.css' />">
<link rel="stylesheet" href="<c:url value='/css/teacherRegist.css' />">
<meta charset="UTF-8">
<title>本の登録</title>
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

<c:if test="${not empty message}">
  <p style="color:green;">${message}</p>
</c:if>

<c:if test="${not empty coverFileName}">
  <p>登録された画像：</p>
  <img src="<c:url value='/img/${coverFileName}' />" alt="表紙画像" width="150">
</c:if>
<div class="book-detail-box">
<form method="POST" action="<c:url value='/BookRegistServlet' />" enctype="multipart/form-data">
  <label>タイトル：
    <input type="text" name="title" placeholder="例）おまえうまそうだな" required>
  </label><br>

  <label>著者：
    <input type="text" name="author" placeholder="例）村上春樹" required>
  </label><br>

  <label>出版社：
    <input type="text" name="publisher" placeholder="例）文藝春秋" required>
  </label><br>

  <label>場所：
    <input type="text" name="gets" placeholder="例）教室" required>
  </label><br>

  <label>ページ数：
    <input type="number" name="page" placeholder="例）300" min="1" required>
  </label><br>

  <label>ジャンル：
    <select name="genre_id" required>
      <option value="" disabled selected hidden>ジャンルを選択</option>
      <option value="1">ファンタジー</option>
      <option value="2">ミステリー</option>
      <option value="3">バトル</option>
      <option value="4">ホラー</option>
      <option value="5">コメディ</option>
      <option value="6">日常</option>
      <option value="7">絵本</option>
      <option value="8">昔話</option>
      <option value="9">文学</option>
      <option value="10">ノンフィクション</option>
      <option value="11">エッセイ</option>
      <option value="12">学習</option>
      <option value="13">図鑑</option>
      <option value="14">雑学</option>
      <option value="15">その他</option>
    </select>
  </label><br>

  <label>表紙画像：
    <input type="file" name="cover" accept="image/*" required>
  </label><br>

  <input type="submit" value="登録">
</form>
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
