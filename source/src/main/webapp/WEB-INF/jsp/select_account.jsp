<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.User" %>

<html>
<head>
    <title>アカウント選択</title>
</head>
<body>
    <h2>アカウント選択画面</h2>

    <%-- エラーメッセージ表示 --%>
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
        <p style="color:red;"><%= errorMessage %></p>
    <%
        }
    %>

    <form method="post" action="SelectAccountServlet">
      

        <ul>
        <%
            List<User> userList = (List<User>) request.getAttribute("userList");
            if (userList != null && !userList.isEmpty()) {
                for (User u : userList) {
        %>
            <li>
                <input type="radio" name="selectedUserId" value="<%= u.getId() %>" id="user_<%= u.getUsers_id() %>" required>
                <label for="user_<%= u.getUsers_id() %>">
                    ユーザー名: <%= u.getName() %> (ID: <%= u.getUsers_id() %>)
                </label>
            </li>
        <%
                }
            } else {
        %>
            <li>アカウントがありません。</li>
        <%
            }
        %>
        </ul>

        <input type="submit" value="ログイン">
    </form>
</body>
</html>

