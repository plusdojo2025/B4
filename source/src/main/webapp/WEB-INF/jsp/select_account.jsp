<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>アカウント選択</title>
  <link rel="stylesheet" href="<c:url value='/css/common.css' />">
  <link rel="stylesheet" href="<c:url value='/css/select_account.css' />">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>

<body>
  <h2>アカウント選択画面</h2>

  <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
  <% if (errorMessage != null) { %>
    <p><%= errorMessage %></p>
  <% } %>

  <form method="post" action="SelectAccountServlet">
    <ul>
      <%-- ① 先に子ども (typeId == 3) を表示 --%>
      <%
        List<User> userList = (List<User>) request.getAttribute("userList");
        if (userList != null) {
          for (User u : userList) {
            if (u != null && u.getTypeId() == 3) { // 子ども
      %>
        <li>
          <label class="account-card">
            <input type="radio" name="selectedUserId" value="<%= u.getId() %>" required>
            <div class="card-inner">
              <i class="fas fa-child"></i>
              <span><%= u.getName() %></span>
            </div>
          </label>
        </li>
      <%
            }
          }

      %>
      <%-- ② 次に保護者 (typeId == 2) を表示 --%>
      <%
          for (User u : userList) {
            if (u != null && u.getTypeId() == 2) { // 保護者
      %>
        <li>
          <label class="account-card">
            <input type="radio" name="selectedUserId" value="<%= u.getId() %>" required>
            <div class="card-inner">
              <i class="fas fa-user-tie"></i>
              <span><%= u.getName() %></span>
            </div>
          </label>
        </li>
      <%
            }
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
