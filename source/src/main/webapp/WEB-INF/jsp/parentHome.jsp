<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム | 保護者ページ</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.9.1/dist/chart.min.js"></script>
</head>
<body>
<h1>よも～にんぐ</h1>
<hr>
<p><a href="/B4/ParentHomeServlet">ホーム</a></p>
<p><a href="/B4/BookListServlet">一覧</a></p>
<p><a href="/B4/BookRecommendServlet">おすすめ</a></p>
<p><a href="/B4/LogoutServlet">ログアウト</a></p>

<label>
    <input type="date" name="startDate">
</label>

<c:forEach var="finbook" items="${finishBookList}">
    <c:url value="/img/${finbook.cover}" var="coverUrl" />
    <div style="display: inline-block; margin: 10px; text-align: center;"><!-- 一時的なCSS -->
        <img src="${coverUrl}" alt="表紙画像" width="150"><br>
        <span style="display: inline-block; max-width: 120px;">${finbook.title}</span>
    </div>
  </c:forEach>

<c:forEach var="pro" items="${progressList}" >
<p>目標${pro.target_page}ページ</p>
<p>進捗${pro.read_page}ページ</p>

<h2><a href="/B4/ProgressServlet">成績表</a></h2>
	<input type="hidden" name="id" value="${pro.id}">
	<h3>${pro.month}月の成績表</h3>
</c:forEach>

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

   	
</body>
</html>