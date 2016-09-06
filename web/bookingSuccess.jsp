<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Success</title>
    </head>
    <body>
        <h3>Successfully Booked!</h3>
        Your booking reference is: <%= session.getAttribute("bookingId")%>
    </body>
</html>
