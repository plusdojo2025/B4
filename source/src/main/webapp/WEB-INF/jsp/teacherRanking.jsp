<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.Ranking" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ランキング　｜　管理者ページ</title>
<link rel="stylesheet" href="<c:url value='/css/techerCommon.css' />">
<link rel="stylesheet" href="<c:url value='/css/ranking.css' />">
</head>
<body>

<div class="page-frame">
<!-- ヘッダー　-->
<header class="header">
<span>
  <c:out value="${sessionScope.user.name}" /> さん
</span>
<!--  <p>リクエスト情報:</p>
<ul>
  <li>月: ${param.month}</li>
  <li>ランキングタイプ: ${param.rankingType}</li>
  <li>クラス: ${param.school_class}</li>
  <li>ジャンルID: ${param.genre_id}</li>
</ul>
-->
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
 
 
<!-- ランキング選択フォーム -->
<form action="RankingServlet" method="get">
  <label for="month">月：</label>
  <input type="month" name="month" id="month" value="${selectedMonth}" required><br><br>
  
  <label for="rankingType">種類を選んでください：</label>
  <select name="rankingType" id="rankingType" onchange="toggleOptions()">
    <option value="class">クラス別</option>
    <option value="genre">ジャンル別</option>
  </select>

  <!-- クラス選択 -->
  <div id="classSelect">
    <label for="school_class">クラス：</label>
    <select name="school_class">
      <option value="1">1組</option>
      <option value="2">2組</option>
      <option value="3">3組</option>
    </select>
  </div>

  <!-- ジャンル選択 -->
  ジャンル：
  <div id="genreSelect">
  <select name="genre_id">
  <option value="">-- 指定なし --</option>
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
  </div>
  <input type="submit" value="検索">

<script>
  function toggleOptions() {
    const type = document.getElementById('rankingType').value;
    document.getElementById('classSelect').style.display = (type === 'class') ? 'block' : 'none';
    document.getElementById('genreSelect').style.display = (type === 'genre') ? 'block' : 'none';
  }
  window.onload = toggleOptions;
</script>
</form>


<h3>${title}</h3>
<table class="status-table" border="1">
  <thead>
    <tr>
      <th>順位</th>
      <th>名前</th>
      <c:if test="${rankingType == 'class'}">
        <th>読んだページ数</th>
      </c:if>
      <c:if test="${rankingType == 'genre'}">
        <th>ジャンル</th>
        <th>読了冊数</th>
      </c:if>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="rank" items="${RankList}" varStatus="status">
      <tr>
        <td>${status.index + 1}</td>
        <td>${rank.name}</td>
        <c:if test="${rankingType == 'class'}">
          <td>${rank.page}</td>
        </c:if>
        <c:if test="${rankingType == 'genre'}">
          <td>${rank.genre_name}</td>
          <td>${rank.f_books}</td>
        </c:if>
      </tr>
    </c:forEach>

    <c:if test="${empty RankList}">
      <tr>
        <td colspan="${rankingType == 'genre' ? 4 : 3}">ランキングデータが見つかりません。</td>
      </tr>
    </c:if>
  </tbody>
</table>
</main>
</div>

<!-- メイン（ここまで） -->

  <!-- フッター（ここから） -->
 <footer class="footer">
 <p class="copyright">&copy;-LEGACY-</p>
 </footer>
 
</body>
</html>