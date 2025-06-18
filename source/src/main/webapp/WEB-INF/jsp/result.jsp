<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>結果画面</title></head>
<body>

<h2>ログイン情報</h2>

<p>ID: <%= request.getAttribute("loginId") %></p>
<p>プロフィール: <%= request.getAttribute("profile") %></p>

<a href="LogoutServlet">ログアウト</a>

</body>
</html>
