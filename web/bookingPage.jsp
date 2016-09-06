<%@page import="java.util.ArrayList"%>
<%@page import="dto.Show"%>
<%@page import="dto.Movie"%>
<%@page import="services.RainbowServices"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        RainbowServices service = new RainbowServices();
        Movie movie = (Movie) session.getAttribute("lastViewedMovie");
        Show show = new Show(); 
        if(session.getAttribute("customer") == null)
            response.sendRedirect("userLoginPage.jsp");
        if(session.getAttribute("showId") == null)
        {
            show = service.getShowInfo(Integer.parseInt(request.getParameter("id")));
            session.setAttribute("showId",show.getShowId());
        }
        else
        {
            show = service.getShowInfo(Integer.parseInt(""+session.getAttribute("showId")));
        }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking - <%= movie.getMovieName()%></title>
    </head>
    <body>
        <div id="info">
            
            <p>Date of the show <%= show.getDate()%></p>
            <p>Time of the show <%= show.getStartTime()%></p>
            <p>Duration <%= movie.getDuration()%></p>
        </div>
        <form action="userBookingAction" method="post">
            <label>Ticket Quantity: </label><input type="text" name="quantity" size="4"/> x $<%= show.getPrice()%>
            <input type="submit" />
        </form>
    </body>
</html>
