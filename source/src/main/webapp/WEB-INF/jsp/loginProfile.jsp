<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>タイプ選択</title></head>
<body>

<h2>どのタイプで操作しますか？</h2>

<form method="post" action="<c:url value='/LoginProfileServlet' />">
    <input type="radio" name="type_id" value="3" required> 保護者<br>
    <input type="radio" name="type_id" value="2" required> 生徒<br>
    <input type="submit" value="送信">
</form>

<c:if test="${not empty errorMessage}">
  <p style="color: red;">${errorMessage}</p>
</c:if>

</body>
</html>
