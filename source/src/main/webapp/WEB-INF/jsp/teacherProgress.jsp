<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, dto.Progress" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, com.google.gson.Gson, dto.Progress" %>

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

<select name="month" onchange="this.form.submit()">
  <option value="">-- 選択 --</option>
            <% 
                int selectedMonth = request.getAttribute("selectedMonth") != null 
                    ? (Integer) request.getAttribute("selectedMonth") 
                    : 0;

                for (int i = 1; i <= 12; i++) {
                    String selected = (i == selectedMonth) ? "selected" : "";
            %>
                <option value="<%= i %>" <%= selected %>><%= i %> 月</option>
            <% } %>
</select>
</form>

<h3>過去の読書記録</h3>

        <h3><%= selectedMonth %> 月の読書記録</h3>
<canvas id="readChart" width="500" height="500"></canvas>

<script>
const jsonData = <%= request.getAttribute("jsonData") %>;
const labels = jsonData.map(pro => pro.day+ "日");
const targetData = jsonData.map(pro >= pro.target_page);
const readData = jsonData.map(pro >= pro.read_page);
    
const data = {
		labels: labels,
        datasets: [
            {
                label: "目標ページ数",
                data: targetData,
                borderColor: "rgba(255,99,132,1)",
                backgroundColor: "rgba(255,99,132,0.2)",
                fill: false,
                tension: 0.2
            },
            {
                label: "読書ページ数",
                data: readData,
                borderColor: "rgba(54,162,235,1)",
                backgroundColor: "rgba(54,162,235,0.2)",
                fill: false,
                tension: 0.2
            }
        ]
    };

    const config = {
        type: 'line',
        data: data,
        options: {
            responsive: true,
            plugins: {
                title: {
                    display: true,
                    text: '日別読書ページ数'
                }
            }
        }
    };

    new Chart(document.getElementById('readChart'), config);
  
</script>

<h3>プロフィール</h3>


<h3>読書傾向</h3>

<form id ="form" method="POST" action="/B4/ProgressServlet">
先生からのコメント<input type="text" name="comment">
<input type="submit" name="submit" value="送信">
</form>

 
</body>
</html>