<!-- result.jsp -->
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>ログイン結果</title></head>
<body>
    <h2>ログイン結果</h2>
    <p>ログインID: <%= request.getAttribute("loginId") %></p>
    <p>役割: <%= request.getAttribute("profile") %></p>

    <p><a href="LoginServlet">ログイン画面に戻る</a></p>
</body>
</html>
