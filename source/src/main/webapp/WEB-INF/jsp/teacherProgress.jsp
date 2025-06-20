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



<form id ="form" method="POST" action="/B4/ProgressServlet">

<select id="select_month">
  <option value="">-- 選択してください --</option>
  <option value="1">1月</option>
  <option value="2">2月</option>
  <option value="3">3月</option>
  <option value="4">4月</option>
  <option value="5">5月</option>
  <option value="6">6月</option>
  <option value="7">7月</option>
  <option value="8">8月</option>
  <option value="9">9月</option>
  <option value="10">10月</option>
  <option value="11">11月</option>
  <option value="12">12月</option>
</select>

<h2 id="output"></h2>

<script>
  const select = document.getElementById('select_month');
  const output = document.getElementById('output');

  select.addEventListener('change', () => {
    const month = select.value;
    if (month) {
      output.textContent = month + '月の成績';
    } else {
      output.textContent = '';
    }
  });
</script>

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

<script>
document.getElementById('form').select.onchange = function() {
	location.href = document.getElementById('form').select.value;
}
</script>
 
</body>
</html>