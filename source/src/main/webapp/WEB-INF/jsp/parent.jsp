<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="dto.User" %>
<html>
<head><title>保護者画面</title></head>
<body>
    <h2>保護者画面</h2>
    <%
        User user = (User) session.getAttribute("user");
        if (user != null) {
    %>
        <p>ログインユーザー名: <%= user.getName() %></p>
        <p>ユーザーID: <%= user.getUsers_id() %></p>
        <p>ユーザータイプID: <%= user.getTypeId() %></p>
    <%
        } else {
    %>
        <p>ユーザー情報がありません。</p>
    <%
        }
    %>
</body>
</html>
