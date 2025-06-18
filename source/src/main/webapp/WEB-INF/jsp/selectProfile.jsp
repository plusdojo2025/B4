<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>プロフィール選択</title></head>
<body>

<h2>プロフィール選択</h2>

<form action="LoginProfileServlet" method="post">
    <label for="profileType">プロフィールを選択してください：</label><br><br>
    <input type="radio" id="parent" name="profileType" value="2" required> 親<br>
    <input type="radio" id="child" name="profileType" value="3"> 子供<br><br>

    <input type="submit" value="選択">
</form>

</body>
</html>
