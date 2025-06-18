<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>成績表 | 管理者ページ</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.9.1/dist/chart.min.js"></script>
<script>
window.onload = function () {
    let context = document.querySelector("#fukuoka_temperature_chart").getContext('2d')
    new Chart(context, {
      type: 'line',
      data: {
        labels: ['1日', '2日', '3日', '4日', '5日', '6日', '7日', '8日', '9日', '10日', '11日', '12日',
        	'13日', '14日', '15日', '16日', '17日', '18日', '19日', '20日', '21日', '22日', '23日', '24日',
        	'25日', '26日', '27日', '28日', '29日', '30日'],
        datasets: [{
          label: "目標",
          data: [8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10],
          borderColor: 'rgba(60, 160, 220, 0.8)'
        }, {
          label: "読了",
          data: [9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 12, 12, 12, 12, 12],
          borderColor: 'rgba(60, 190, 20, 0.8)'
        }],
      },
      options: {
        responsive: false,
      }
    })
  }
</script>
</head>
<body>
<h1>よも～にんぐ</h1>
<hr>
<p><a href="/B4/studentHomeServlet">ホーム</a></p>
<p><a href="/B4/UpdateDeleteServlet">登録</a></p>
<p><a href="/B4/BookListServlet">一覧</a></p>
<p><a href="/B4/BookRecommendServlet">おすすめ</a></p>
<p><a href="/B4/OpinionServlet">目安箱</a></p>
<p><a href="/B4/RankingServlet">ランキング</a></p>
<p><a href="/B4/LogoutServlet">ログアウト</a></p>



<form id ="form" method="POST" action="/B4/ProgressServlet">

<select id="select_month">

<c:forEach  begin="1" end="12" step="1" var="i">
	<option><c:out value="${i}" /></option>  
	</c:forEach>
</select>

 <c:forEach var="pro" items="${progressList}">
	<input type="hidden" name="id" value="${pro.id}">
	<p>${pro.user_id}さん</p>
	<p>${pro.month}月の成績表</p>
	<h3>過去の読書記録</h3>
	<p>${pro.target_page}</p>
	<p>${pro.read_page}</p>

</c:forEach>
   
<canvas id="fukuoka_temperature_chart" width="500" height="500"></canvas>
<h3>プロフィール</h3>


<h3>読書傾向</h3>


先生からのコメント<input type="text" name="comment">
<input type="submit" name="submit" value="送信">
</form>

<script>
document.getElementById('form').select.onchange = function() {
	location.href = document.getElementById('form').select.value;
}
</script>
 
</body>
</html>