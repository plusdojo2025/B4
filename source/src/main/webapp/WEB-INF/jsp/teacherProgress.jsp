<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, dto.Progress" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.*, com.google.gson.Gson, dto.Progress" %>
<%

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>成績表 | 管理者ページ</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.9.1/dist/chart.min.js"></script>
</head>
<body>
<h1>よも～にんぐ</h1>
<hr>
<p><a href="/B4/teacherHomeServlet">ホーム</a></p>
<p><a href="/B4/UpdateDeleteServlet">登録</a></p>
<p><a href="/B4/BookListServlet">一覧</a></p>
<p><a href="/B4/BookRecommendServlet">おすすめ</a></p>
<p><a href="/B4/OpinionServlet">目安箱</a></p>
<p><a href="/B4/RankingServlet">ランキング</a></p>
<p><a href="/B4/LogoutServlet">ログアウト</a></p>

<form id ="form_get" method="GET" action="/B4/ProgressServlet">

<select id="month">
  <% for (int i = 1; i <= 12; i++) { %>
            <option value="<%= i %>"><%= i %> 月</option>
        <% } %>
</select>

<h2 id="output"></h2>

<script src="js/pulldown.js"></script>

</form>

<form id ="form" method="POST" action="/B4/ProgressServlet">

<h3>過去の読書記録</h3>

<script>
const chartData = JSON.parse('<%= session.getAttribute("chartData") %>');

window.onload = function () {
    let context = document.querySelector("#read_book_chart").getContext('2d')
    new Chart(context, {
      type: 'line',
      data: {
        labels: chartData.labels,
        datasets: [{
          label: "目標",
          data: chartData.targetData,
          borderColor: 'rgba(60, 160, 220, 0.8)'
        }, {
          label: "読了",
          data: chartData.readData,
          borderColor: 'rgba(60, 190, 20, 0.8)'
        }],
      },
      options: {
        responsive: false,
        scales: {
            x: {
                title: {
                    display: true,
                    text: '日付'
                }
            },
            y: {
                beginAtZero: true,
                title: {
                    display: true,
                    text: 'ページ数'
                }
            }
        }
      }
    })
  }
</script>

<canvas id="read_book_chart" width="500" height="500"></canvas>
<h3>プロフィール</h3>


<h3>読書傾向</h3>


先生からのコメント<input type="text" name="comment">
<input type="submit" name="submit" value="送信">
</form>

 
</body>
</html>