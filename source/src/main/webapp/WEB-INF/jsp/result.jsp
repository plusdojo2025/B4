<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="dto.User" %>
<html>
<head><title>結果画面</title></head>
<body>

<%
    User user = (User) session.getAttribute("user");
    if (user != null) {
%>

    <h1><%= user.getName() %> </h1>

  <% if (user.getTypeId() == 1) { %>
    <p>管理者。</p>
<% } else if (user.getTypeId() == 2) { %>
    <p>保護者</p>
<% } else if (user.getTypeId() == 3) { %>
    <p>生徒。</p>
<% } else { %>
    <p>未設定</p>
<% } %>


<%  } else { %>
    <p>だめ</p>
<% } %>

</body>
</html>
