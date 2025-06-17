<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Progress" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    List<Progress> progressList = (List<Progress>) request.getAttribute("progressList");
    SimpleDateFormat sdf = new SimpleDateFormat("d"); // 日のみ
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>成績表 | 管理者ページ</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
<select id="select_month" name="month">
<c:forEach  begin="1" end="12" step="1" var="i">
	<option><c:out value="${i}" /></option>  
	</c:forEach>
</select>

<h2>6月の成績表</h2>

<canvas id="readingChart" width="600" height="300"></canvas>

<h3>過去の読書記録</h3>

<h3>プロフィール</h3>


<h3>読書傾向</h3>


先生からのコメント<input type="text" name="comment">
<input type="submit" name="submit" value="送信">
</form>

<script>
document.getElementById('form_month').select.onchange = function() {
	location.href = document.getElementById('form_month').select.value;
}
</script>
 <script>
        const labels = [
            <% for (Progress log : progressList) { %>
                "<%= sdf.format(log.getUpdated_at()) %>",
            <% } %>
        ];

        const data = {
            labels: labels,
            datasets: [{
                label: '読んだページ数',
                data: [
                    <% for (Progress log : progressList) { %>
                        <%= log.getRead_page() %>,
                    <% } %>
                ],
                backgroundColor: 'rgba(54, 162, 235, 0.5)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }]
        };

        new Chart(document.getElementById('readingChart'), {
            type: 'bar',
            data: data,
            options: {
                scales: {
                    y: { beginAtZero: true }
                }
            }
        });
    </script>

</body>
</html>