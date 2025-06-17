<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>プロフィール表示</title></head>
<body>
    <h2>ログインプロフィール</h2>
    
    <p>区分:<strong><%= request.getAttribute("profile") %></strong> </p>
    
    <p><a href="LoginServlet">ログイン画面に戻る</a></p>
</body>
</html>
