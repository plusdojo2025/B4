<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>ログイン画面</title></head>
<body>

<h2>ログイン</h2>

<% String errorMessage = (String) request.getAttribute("errorMessage"); %>
<% if (errorMessage != null) { %>
    <p style="color:red;"><%= errorMessage %></p>
<% } %>


<form method="post" action="LoginServlet">
    ユーザー：<input type="text" name="users_id">
    パスワード：<input type="password" name="password"><br>
    <input type="submit" value="ログイン">
</form>

</body>
</html>
