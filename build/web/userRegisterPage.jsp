<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rainbow - Register</title>
    </head>
    <body>
        <form action ="userRegisterProcessing" method ="post">
            <label>Name</label><input type="text" name="name" />
            <label>Date of birth</label><input type="date" name="dob" />
            <label>Address</label><input type="text" name="address" />
            <label>Phone No</label><input type="tel" name="phone_no" />
            <label>Email ID</label><input type="email" name="email_id" />
            <label>Password</label><input type="password" name="password" />
            <label>Re-type Password</label><input type="password" name="r_password" />
            <input type="submit" value="Register"/>
        </form>
    </body>
</html>
