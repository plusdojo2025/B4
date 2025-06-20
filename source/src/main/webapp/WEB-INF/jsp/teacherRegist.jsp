<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>本の登録</title>
</head>
<body>

<header>
<span>
  <c:out value="${sessionScope.user.name}" /> さん
</span>
<p><a href="<c:url value='/StudentHomeServlet' />">ホーム</a></p>
<p><a href="<c:url value='/RegistServlet' />">登録</a></p>
<p><a href="<c:url value='/BookListServlet' />">一覧</a></p>
<p><a href="<c:url value='/BookRecommendServlet' />">おすすめ</a></p>
<p><a href="<c:url value='/OpinionServlet' />">目安箱</a></p>
<p><a href="<c:url value='/RankingServlet' />">ランキング</a></p>
<p><a href="<c:url value='/LogoutServlet' />">ログアウト</a></p>
</header>

<h2>本の登録フォーム</h2>

<c:if test="${not empty message}">
  <p style="color:green;">${message}</p>
</c:if>

<c:if test="${not empty coverFileName}">
  <p>登録された画像：</p>
  <img src="<c:url value='/img/${coverFileName}' />" alt="表紙画像" width="150">
</c:if>

<form method="POST" action="<c:url value='/BookRegistServlet' />" enctype="multipart/form-data">
  <label>タイトル：<input type="text" name="title" placeholder="例）山田太郎"></label><br>
  <label>著者：<input type="text" name="author" placeholder="例）村上春樹"></label><br>
  <label>出版社：<input type="text" name="publisher" placeholder="例）文藝春秋"></label><br>
  <label>場所：<input type="text" name="gets" placeholder="例）教室"></label><br>
  <label>ページ数：<input type="number" name="page" placeholder="例）300"></label><br>
  <label>
  ジャンル：
  <select name="genre_id">
  <option value="1" <c:if test="${param.genreId == '1'}">selected</c:if>>ファンタジー</option>
  <option value="2" <c:if test="${param.genreId == '2'}">selected</c:if>>ミステリー</option>
  <option value="3" <c:if test="${param.genreId == '3'}">selected</c:if>>バトル</option>
  <option value="4" <c:if test="${param.genreId == '4'}">selected</c:if>>ホラー</option>
  <option value="5" <c:if test="${param.genreId == '5'}">selected</c:if>>コメディ</option>
  <option value="6" <c:if test="${param.genreId == '6'}">selected</c:if>>日常</option>
  <option value="7" <c:if test="${param.genreId == '7'}">selected</c:if>>絵本</option>
  <option value="8" <c:if test="${param.genreId == '8'}">selected</c:if>>昔話</option>
  <option value="9" <c:if test="${param.genreId == '9'}">selected</c:if>>文学</option>
  <option value="10" <c:if test="${param.genreId == '10'}">selected</c:if>>ノンフィクション</option>
  <option value="11" <c:if test="${param.genreId == '11'}">selected</c:if>>エッセイ</option>
  <option value="12" <c:if test="${param.genreId == '12'}">selected</c:if>>学習</option>
  <option value="13" <c:if test="${param.genreId == '13'}">selected</c:if>>図鑑</option>
  <option value="14" <c:if test="${param.genreId == '14'}">selected</c:if>>雑学</option>
  <option value="15" <c:if test="${param.genreId == '15'}">selected</c:if>>その他</option>    
  </select>
  </label><br>
  <label>表紙画像：<input type="file" name="cover" accept="image/*"></label><br>
  <input type="submit" value="登録">
</form>

</body>
</html>
