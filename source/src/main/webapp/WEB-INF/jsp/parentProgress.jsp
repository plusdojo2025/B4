<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, dto.Progress" %>
<%
    String jsonProgress = (String) request.getAttribute("jsonProgress");
    if (jsonProgress == null) jsonProgress = "[]";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>成績表 | 保護者ページ</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.9.1/dist/chart.min.js"></script>
</head>
<body>
<h1>よも～にんぐ</h1>
<hr>
<p><a href="/B4/ParentHomeServlet">ホーム</a></p>
<p><a href="/B4/BookListServlet">一覧</a></p>
<p><a href="/B4/BookRecommendServlet">おすすめ</a></p>
<p><a href="/B4/LogoutServlet">ログアウト</a></p>


<label>月を選択：</label>
<select name="month" id="monthSelect">
    <c:forEach var="i" begin="1" end="12">
        <option value="${i}">${i}月</option>
    </c:forEach>
</select>

<h3>過去の読書記録</h3>

<div style="position:relative;width:800px;height:400px;">
<canvas id="progressChart"></canvas>
</div>
<script>
const progressList = JSON.parse('<%= jsonProgress.replace("\\", "\\\\").replace("'", "\\'") %>');

const ctx = document.getElementById('progressChart').getContext('2d');
const chart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: Array.from({ length: 31 }, (_, i) => i + 1), 
        datasets: [{
            label: 'もくひょう',			//凡例のラベル
            data: Array(31).fill(0),
            fill: false,
            tension: 0.1,
            borderColor: 'rgba(60, 160, 220, 0.8)',
            pointRadius: 4,
            pointHoverRadius: 6
        },{
            label: 'よんだページ',			//凡例のラベル
            data: Array(31).fill(0),
            fill: false,
            tension: 0.1,
            borderColor: 'rgba(60, 190, 20, 0.8)',
            pointRadius: 4,
            pointHoverRadius: 6
        }]
    },
    options: {
        responsive: true,
        scales: {
            y: {
                beginAtZero: true,
                title: {
                    display: true,
                    text: 'ページ数'
                }
            },
            x: {
                title: {
                    display: true,
                    text: '日付（日）'
                },
                ticks: {
                    autoSkip: false,     // 全部表示（必要に応じて調整）
                    maxRotation: 0,
                    minRotation: 0
                }
            }
        }
    }
});
//月によってデータをフィルタリングして、グラフにする処理
function updateChartByMonth(monthStr) {
    const selectedMonth = parseInt(monthStr);
    const filtered = progressList.filter(p => parseInt(p.month) === selectedMonth);

    const dayTarData = Array(31).fill(0);
    for (const p of filtered) {
        const dayTarIndex = parseInt(p.day) - 1;
        if (dayTarIndex >= 0 && dayTarIndex < 31) {
        	
            dayTarData[dayTarIndex] = parseInt(p.target_page) || 0;		//read_pageのみ
            
        }
    }
    
    const dayData = Array(31).fill(0);
    for (const p of filtered) {
        const dayIndex = parseInt(p.day) - 1;
        if (dayIndex >= 0 && dayIndex < 31) {
        	
            dayData[dayIndex] = parseInt(p.read_page) || 0;		//read_pageのみ
            
        }
    }
 
	chart.data.datasets[0].data = dayTarData;
    chart.data.datasets[1].data = dayData;
    chart.update();

}
//プルダウンメニューが変更された時の処理
document.addEventListener("DOMContentLoaded", function () {
    const monthSelect = document.getElementById("monthSelect");

    const now = new Date();
    const currentMonth = now.getMonth() + 1;
    monthSelect.value = currentMonth;

    updateChartByMonth(currentMonth);

    monthSelect.addEventListener("change", function () {
        updateChartByMonth(this.value);
    });
});
</script>

</body>
</html>