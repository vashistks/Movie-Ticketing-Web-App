<%@page import="dto.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.Show"%>
<%@page import="dto.Movie"%>
<%@page import="services.RainbowServices"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        RainbowServices service = new RainbowServices();
        Movie movie = service.getMovieInfo(Integer.parseInt(request.getParameter("id")));
        ArrayList<Integer> maxHallCapacity = new ArrayList<Integer>();
        ArrayList<Show> shows = service.getCurrentShows(Integer.parseInt(request.getParameter("id")), maxHallCapacity);
        session.setAttribute("lastViewedMovie", movie);
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rainbow - <%= movie.getMovieName()%></title>
    </head>
    <body>
        <%
        if(session.getAttribute("customer") == null)
            {
        %>
            <a href="userRegisterPage.jsp">Register</a>
            <a href="userLoginPage.jsp">Login</a>
        <%
            
            
            }
            else
            {
                String name = ((Customer)session.getAttribute("customer")).getName();
                %>
            Welcome <%= name %>
            <a href="userLogoutProcessing.jsp">Logout</a>
            <a href="userSettingsPage.jsp">Settings</a>
                <%
            }
        %>
        <div id="movieInfoBlock">
            <div id="coverImage">
                <img src="<%=movie.getCover()%>">
            </div>
            <div id="info">
                <%= movie.getMovieName()%>
                <p><h3>Description</h3> <%= movie.getDescription()%>
                <p><h3>Duration</h3> <%= movie.getDuration()%>
                <p><h3>Director</h3> <%= movie.getDirector()%>
                <p><h3>Cast</h3> <%= movie.getMovieCast()%>
                <p><h3>Release Date</h3> <%= movie.getReleaseDate()%>
            </div>
        </div>
        <div id="showsInfoBlock">
            <h3>Show Timings</h3>
            <table>
                <tr><th>Date</th><th>Time</th><th>Status</th></tr>
                <%
                    int index = 0;
                    for(Show s: shows)
                    {
                %>
                <tr><td><%= s.getDate()%></td><td><%= s.getStartTime()%></td>
                <%
                    if(maxHallCapacity.get(index) <= s.getTicketsBooked())
                       {
                %>
                            <td>Full Booked</td></tr>
                      <%
                        }
                     else{
                      %>
                            <td><a href="bookingPage.jsp?id=<%=s.getShowId()%>">Available</a></td></tr>
                      <%
                          }
                      %>
                      
                <%
                    }
                %>
            </table>
        </div>
    </body>
</html>
