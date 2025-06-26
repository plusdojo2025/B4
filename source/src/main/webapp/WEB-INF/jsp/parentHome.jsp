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
<ul>
<li><a href="<c:url value='/ParentHomeServlet' />">ホーム</a></li>
<li><a href="<c:url value='/BookListServlet' />">一覧</a></li>
<li><a href="<c:url value='/BookRecommendServlet' />">おすすめ</a></li>
<li> <button class="logout-btn" onclick="location.href='<c:url value='/LogoutServlet'/>'">ログアウト</button></li>
</ul>

<label>
    <input type="date" name="startDate">
</label>

<c:if test="${not empty currentBook}">
  <c:url value="/img/${currentBook.cover}" var="coverUrl" />
  <div style="display: flex; align-items: center; margin-bottom: 30px;">
    
    <!-- 表紙画像をリンク化して BookDetailServlet へ -->
    <a href="${pageContext.request.contextPath}/BookDetailServlet?bookId=${currentBook.book_id}&lastList=RecordServlet">
    	<img src="${coverUrl}" alt="表紙画像" width="150" style="margin-right: 20px;">
    </a>

    <div>
      <p>今読んでいる本は「${currentBook.title}」</p>
      <c:choose>
        <c:when test="${not empty todayProgress}">
          <p>${todayProgress.month}月${todayProgress.day}日</p>
          <p>目標：${todayProgress.target_page}ページ</p>
          <p>読んだページ：${todayProgress.read_page}ページ</p>
        </c:when>
        <c:otherwise>
          <p style="color: red; font-weight: bold;">今日の読書記録はありません。</p>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
</c:if>

<h2><a href="<c:url value='/ProgressServlet' />">成績表</a></h2>

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

<canvas id="read_book_chart" width="300" height="300"></canvas>

   	
</body>
</html>