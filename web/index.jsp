<%@page import="dto.Movie"%>
<%@page import="java.util.ArrayList"%>
<%@page import="services.RainbowServices"%>
<%@page import="dto.Customer"%>
<%@page import="dto.Customer"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rainbow Entertainment</title>
    </head>
    <body>
        <%
            RainbowServices services = new RainbowServices();
            ArrayList<Movie> latestMovies = services.getLatestCoverAndTitle();
            ArrayList<Movie> runningMovies = services.getCurrentlyRunningTitles();
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
        <div id="lastestTitles">
            <h3>Latest Titles</h3>
        <table>
            <%
                for(Movie m: latestMovies)
                {
            %>
            <tr><a href="movieInfo.jsp?id=<%= m.getMovieId() %>"><img src="<%=m.getCover()%>"/></a></tr>
            <tr><%= m.getMovieName() %></tr>
                
            <%
                }
            %>
        </table>
        </div>
        
        <div id="runningTitles">
            <h3>Also showing</h3>
        <table>
            <%
                for(Movie m: runningMovies)
                {
            %>
            <tr><a href="movieInfo.jsp?id=<%= m.getMovieId() %>"><img src="<%= m.getCover()%>"/></a></tr>
            <tr><%= m.getMovieName() %></tr>
                
            <%
                }
            %>
        </table>
        </div>
    </body>
</html>
