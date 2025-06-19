<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>タイプ選択</title></head>
<body>

<h2>選択してください</h2>

<form method="post" action="/B4/LoginProfileServlet">


    <input type="radio" name="type_id" value="2" required> 保護者<br>
    <input type="radio" name="type_id" value="3" required> 生徒<br>
    <input type="submit" value="送信">
</form>

</body>
</html>
