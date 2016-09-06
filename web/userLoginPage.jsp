
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rainbow - Login</title>
    </head>
    <body>
        <form action="userLoginProcessing" method="post">
           <label>Email ID</label><input type="text" name="email_id" />
           <label>Password</label><input type="password" name="password" />
           <input type="submit" value="Login In"/>
        </form>
    </body>
</html>
