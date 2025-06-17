<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>ログインフォーム</title></head>
<body>
<h2>ログインフォーム</h2>

<% String errorMessage = (String) request.getAttribute("errorMessage"); %>
<% if (errorMessage != null) { %>
    <p style="color:red;"><%= errorMessage %></p>
<% } %>

<form action="LoginServlet" method="post">
    ID: <input type="text" name="id" required /><br/>
    パスワード: <input type="password" name="pw" required /><br/>
    <input type="submit" value="ログイン" />
</form>
</body>
</html>
