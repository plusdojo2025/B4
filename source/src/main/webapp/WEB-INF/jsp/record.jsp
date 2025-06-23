<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>きろく | 生徒ページ</title>
</head>
<body>
<h1>よも～にんぐ</h1>
<hr>
<p><a href="/B4/CollectionServlet">コレクション</a></p>
<p><a href="/B4/studentHomeServlet">ホーム</a></p>
<p><a href="/B4/BookListServlet">ほんだな</a></p>
<p><a href="/B4/BookRecommendServlet">おすすめ</a></p>
<p><a href="/B4/RecordServlet">きろく</a></p>
<p><a href="/B4/ProgressServlet">せいせき</a></p>
<p><a href="/B4/LogoutServlet">ログアウト</a></p>

<c:forEach var="pro" items="${progressList}">
	<input type="hidden" name="id" value="${pro.id}">
<h2>${pro.user_id}さんのきろく</h2>

<div id="mask" class="hidden"></div>

<div class="container">
    <div class="card hidden">
        <p>よんだ日　6月17日</p>
        <p>もくひょう　${pro.target_page}</p>
        <p>よんだページ　${pro.read_page}</p>
        <p>タイトル　${pro.book_id}</p>
        <button id="close">とじる！</button>
    </div>
</div>

<button id="open">${pro.book_id}</button>
</c:forEach>

<style>

.container {
    width: 1000px;
    margin: 200px auto;
}
.card {
    width: 400px;
    border: 2px solid #333;
    border-radius: 4px;
    padding: 30px;
    list-style-position: inside;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: white;
    transition-duration: 1s;
    z-index: 2;
}
#mask {
    position: fixed;
    top: 0;
    left: 0;
    bottom: 0;
    right: 0;
    background-color: rgba(0, 0, 0, 0.3);
    transition-duration: 1s;
    z-index: 1;
}
.hidden {
    display: none;
}
</style>

<script>
const card = document.querySelector('.card');
const open = document.getElementById('open');
const close = document.getElementById('close');
const mask = document.getElementById('mask');

open.addEventListener('click', () => {
  card.classList.toggle('hidden');
  mask.classList.toggle('hidden');
  open.classList.toggle('hidden');
});

close.addEventListener('click', () => {
  card.classList.toggle('hidden');
  mask.classList.toggle('hidden');
  open.classList.toggle('hidden');
});
</script>

</body>
</html>