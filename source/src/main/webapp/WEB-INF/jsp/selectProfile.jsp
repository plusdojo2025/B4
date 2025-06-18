<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>プロフィール種別選択</title></head>
<body>
    <h2>どちらとしてログインしますか？</h2>
    <form action="LoginProfileServlet" method="post">
        <input type="radio" name="profileType" value="2" required> 保護者<br>
        <input type="radio" name="profileType" value="3"> 生徒<br><br>
        <input type="submit" value="決定">
    </form>
</body>
</html>
