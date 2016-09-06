package services;
import dao.RainbowDAO;
import dto.*;
import java.sql.Connection;
import java.sql.Statement;
import dao.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class RainbowServices
{
    public Customer getCustomer(String emailId)throws Exception
    {
        Customer customer = null;
        Connection conn = RainbowDAO.getConnection();
        Statement st = conn.createStatement();
        String sql = "select * from customer where email_id = '"+emailId+"'";
        ResultSet rs = st.executeQuery(sql);
        while(rs.next())
        {
                customer = createCustomerObject(rs);
        }
        RainbowDAO.close(rs);
        RainbowDAO.close(st);
        RainbowDAO.close(conn);
        return customer;
    }
    
    public boolean authenticate(String emailId, String password)throws Exception
    {
        Connection conn = RainbowDAO.getConnection();
        Statement st = conn.createStatement();
        String sql = "select user_password from customer where email_id = '"+emailId+"'";
        ResultSet rs = st.executeQuery(sql);
        while(rs.next())
        {
                if(password.equals(rs.getString(1)))
                    return true;
        }
        RainbowDAO.close(rs);
        RainbowDAO.close(st);
        RainbowDAO.close(conn);

        return false;
    }
    public Customer createCustomerObject (ResultSet rs)throws Exception
    {
        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("customer_id"));
        customer.setName(rs.getString("customer_name"));
        customer.setAddress(rs.getString("address"));
        customer.setDob(rs.getDate("dob"));
        customer.setEmailId(rs.getString("email_id"));
        customer.setPhoneNo(rs.getString("phone_no"));
        customer.setUserPassword(rs.getString("user_password"));
        return customer;
    }
    public Customer createCustomerObject (HttpServletRequest request)throws Exception
    {
        Customer customer = new Customer();
        customer.setAddress(request.getParameter("address"));
        customer.setName(request.getParameter("name"));
        customer.setEmailId(request.getParameter("email_id"));
        customer.setPhoneNo(request.getParameter("phone_no"));
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-DD");
        java.util.Date date = sdf1.parse(request.getParameter("dob"));
        java.sql.Date sqlStartDate = new Date(date.getTime()); 
        customer.setDob(sqlStartDate);
        customer.setUserPassword(request.getParameter("password"));
        return customer;
    }
    public Customer saveUser(Customer customer)throws Exception
    {
        int maxId = 0;
        Connection conn = RainbowDAO.getConnection();
        Statement st = conn.createStatement();
        String sql_getMaxID = "select max(customer_id) from customer";
        ResultSet rs = st.executeQuery(sql_getMaxID);
        while(rs.next())
        {
            maxId = rs.getInt(1);
        }
        customer.setCustomerId(++maxId);
        String str = "insert into customer(customer_name, customer_id, address, phone_no, dob, email_id, user_password) values(?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(str);
        ps.setString(1, customer.getName());
        ps.setInt(2, customer.getCustomerId());
        ps.setString(3, customer.getAddress());
        ps.setString(4, customer.getPhoneNo());
        ps.setDate(5, customer.getDob());
        ps.setString(6, customer.getEmailId());
        ps.setString(7,customer.getUserPassword());
        
        ps.executeUpdate();
        RainbowDAO.close(rs);
        RainbowDAO.close(st);
        RainbowDAO.close(conn);
        
        return customer;
    }
    
    public ArrayList<Movie> getLatestCoverAndTitle()throws Exception
    {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        Connection conn = RainbowDAO.getConnection();
        Statement st = conn.createStatement();
        String sql = "select cover_path, movie_name, movie_id from movie where release_date > cast ({fn TIMESTAMPADD(SQL_TSI_DAY, -10, CURRENT_DATE)} as DATE)";
        ResultSet rs = st.executeQuery(sql);
        while(rs.next())
        {
            Movie movie = new Movie();
            movie.setCover(rs.getString("cover_path")); /* Needs to be implemented */
            movie.setMovieName(rs.getString("movie_name"));
            movie.setMovieId(rs.getInt("movie_id"));
            movies.add(movie);
        }
        RainbowDAO.close(rs);
        RainbowDAO.close(st);
        RainbowDAO.close(conn);
        
        return movies;
    }
    
    public ArrayList<Movie> getCurrentlyRunningTitles()throws Exception
    {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        Connection conn = RainbowDAO.getConnection();
        Statement st = conn.createStatement();
        String sql = "select cover_path, movie_name, movie_id from movie where movie_id not in (select movie_id from movie where release_date > cast ({fn TIMESTAMPADD(SQL_TSI_DAY, -10, CURRENT_DATE)} as DATE)) and movie_id in (select movie_id from show where date >= CURRENT_DATE)";
        ResultSet rs = st.executeQuery(sql);
        while(rs.next())
        {
            Movie movie = new Movie();
            movie.setCover(rs.getString("cover_path")); /* Needs to be implemented */
            movie.setMovieName(rs.getString("movie_name"));
            movie.setMovieId(rs.getInt("movie_id"));
            movies.add(movie);
        }
        RainbowDAO.close(rs);
        RainbowDAO.close(st);
        RainbowDAO.close(conn);
        
        return movies;
    }
    public Movie getMovieInfo(int movieId)throws Exception
    {
        Movie movie = new Movie();
        Connection conn = RainbowDAO.getConnection();
        Statement st = conn.createStatement();
        String sql = "select * from movie where movie_id = "+movieId; 
        ResultSet rs = st.executeQuery(sql);
        while(rs.next())
        {
            movie.setCover(rs.getString("cover_path")); /* Needs to be implemented */
            movie.setMovieName(rs.getString("movie_name"));
            movie.setMovieId(rs.getInt("movie_id"));
            movie.setCategory(rs.getString("category"));
            movie.setDescription(rs.getString("description"));
            movie.setDirector(rs.getString("director"));
            movie.setMovieCast(rs.getString("movie_cast"));
            movie.setDuration(rs.getTime("duration"));
            movie.setReleaseDate(rs.getDate("release_date"));
        }
        RainbowDAO.close(rs);
        RainbowDAO.close(st);
        RainbowDAO.close(conn);
        
        return movie;
    }
    public ArrayList<Show> getCurrentShows(int movieId, ArrayList<Integer> maxHallCapacity) throws Exception
    {
        ArrayList<Show> shows = new ArrayList<Show>();
        Connection conn = RainbowDAO.getConnection();
        Statement st = conn.createStatement();
        String sql = "select * from show where movie_id = "+movieId+" and date >= CURRENT_DATE"; 
        ResultSet rs = st.executeQuery(sql);
        while(rs.next())
        {
            shows.add(createShowObject(rs, maxHallCapacity));
        }
        RainbowDAO.close(rs);
        RainbowDAO.close(st);
        RainbowDAO.close(conn);
        
        return shows;
    }
    private Show createShowObject(ResultSet rs, ArrayList<Integer> maxHallCapacity)throws Exception
    {
        Show show = new Show();
        show.setDate(rs.getDate("date"));
        show.setEndTime(rs.getTime("end_time"));
        show.setStartTime(rs.getTime("end_time"));
        show.setHallId(rs.getInt("hall_id"));
        show.setMovieId(rs.getInt("movie_id"));
        show.setShowId(rs.getInt("show_id"));
        show.setTicketsBooked(rs.getInt("tickets_booked"));
        show.setPrice(rs.getInt("price"));
        String sql = "select capacity from hall where hall_id = "+show.getHallId();
        Connection conn = RainbowDAO.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs2 = st.executeQuery(sql);
        while(rs2.next())
        {
            maxHallCapacity.add(rs2.getInt(1));
        }
        RainbowDAO.close(rs2);
        RainbowDAO.close(st);
        RainbowDAO.close(conn);
        
        return show;
    }
    public Show getShowInfo(int showId)throws Exception
    {
        Show show = new Show();
        Connection conn = RainbowDAO.getConnection();
        Statement st = conn.createStatement();
        String sql = "select * from show where show_id = "+showId; 
        ResultSet rs = st.executeQuery(sql);
        while(rs.next())
        {
            show.setDate(rs.getDate("date"));
            show.setEndTime(rs.getTime("end_time"));
            show.setStartTime(rs.getTime("end_time"));
            show.setHallId(rs.getInt("hall_id"));
            show.setMovieId(rs.getInt("movie_id"));
            show.setShowId(rs.getInt("show_id"));
            show.setTicketsBooked(rs.getInt("tickets_booked"));
            show.setPrice(rs.getInt("price"));
        }
        RainbowDAO.close(rs);
        RainbowDAO.close(st);
        RainbowDAO.close(conn);
        
        return show;
    }
    public int bookTickets(Customer customer, int showId, int quantity)throws Exception
    {
        int maxBookingId = 0, maxCapacity = 0;
        Show show = this.getShowInfo(showId);
        Connection conn = RainbowDAO.getConnection();
        Statement st = conn.createStatement();
        String sql = "select max(booking_id) from booking"; 
        String sql2 = "select capacity from hall where hall_id = "+show.getHallId();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next())
        {
            maxBookingId = rs.getInt(1);
        }
        maxBookingId++;
        ResultSet rs2 = st.executeQuery(sql2);
        while(rs2.next())
        {
            maxCapacity = rs2.getInt(1);
        }
        if(maxCapacity < show.getTicketsBooked()+ quantity)
        {
            RainbowDAO.close(rs);
            RainbowDAO.close(rs2);
            RainbowDAO.close(st);
            RainbowDAO.close(conn);
            return -1;
        }
        int updatedBookedCount = show.getTicketsBooked()+quantity;
        String sql3 = "update show set tickets_booked = "+updatedBookedCount+" where show_id = "+show.getShowId();
        st.executeUpdate(sql3);
        String sql4 = "insert into booking (show_id, no_tickets, amount, booking_id, customer_id) values(?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql4);
        double price = show.getPrice();
        price = quantity * price;
        
        ps.setInt(1, showId);
        ps.setInt(2, quantity);
        ps.setDouble(3, price);
        ps.setInt(4, maxBookingId);
        ps.setInt(5, customer.getCustomerId());
        ps.executeUpdate();
        
        RainbowDAO.close(rs);
        RainbowDAO.close(rs2);
        RainbowDAO.close(st);
        RainbowDAO.close(conn);
        return maxBookingId;
    }
    
    
    public Customer updateCustomer (Customer customer)throws Exception
    {
        int customerId = 0;
        Connection conn = RainbowDAO.getConnection();
        Statement st = conn.createStatement();
        String sql_getMaxID = "select customer_id from customer where email_id = '"+customer.getEmailId()+"'";
        ResultSet rs = st.executeQuery(sql_getMaxID);
        while(rs.next())
        {
            customerId = rs.getInt(1);
        }
        customer.setCustomerId(customerId);
        String str = "update customer set customer_name = ?, address = ?, user_password = ?, phone_no = ? where customer_id = "+customerId;
        PreparedStatement ps = conn.prepareStatement(str);
        ps.setString(1, customer.getName());
        ps.setString(2, customer.getAddress());
        ps.setString(4, customer.getPhoneNo());
        ps.setString(3,customer.getUserPassword());
        
        ps.executeUpdate();
        RainbowDAO.close(rs);
        RainbowDAO.close(st);
        RainbowDAO.close(conn);
        
        return customer;
    }
    
    public Customer createCustomerObjectForUpdate (HttpServletRequest request)throws Exception
    {
        Customer customer = new Customer();
        customer.setAddress(request.getParameter("address"));
        customer.setName(request.getParameter("name"));
        customer.setPhoneNo(request.getParameter("phone_no"));
        customer.setUserPassword(request.getParameter("password"));
        return customer;
    }
}
