
<%@page import="dto.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        Customer customer = (Customer) (session.getAttribute("customer"));
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Settings - <%= customer.getName()%></title>
    </head>
    <body>
        <form action ="userProfileAction" method ="post">
            <label>Name</label><input type="text" name="name" value="<%=customer.getName()%>"/>
            <label>Address</label><input type="text" name="address" value="<%=customer.getAddress()%>"/>
            <label>Phone No</label><input type="tel" name="phone_no" value="<%=customer.getPhoneNo()%>"/>
            <label>Current Password</label><input type="password" name="current_password" />
            <label>Password</label><input type="password" name="password" />
            <label>Re-type Password</label><input type="password" name="r_password" />
            <input type="submit" value="Submit"/>
        </form>        
    </body>
</html>
