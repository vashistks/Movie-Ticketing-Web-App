package dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class RainbowDAO
{

    private static final DataSource getRainbowDBReference() throws NamingException
    {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/rainbowDBReference");
    }
    
    public static final Connection getConnection() throws Exception
    {
           DataSource dataSource = getRainbowDBReference();
           return dataSource.getConnection();
    }
    public static final void close(ResultSet rs)
    {
        if (rs != null)
        {
            try
            {
                rs.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
    public static final void close(Statement st)
    {
        if (st != null)
        {
            try
            {
                st.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }       
    }
    public static final void close(Connection conn)
    {
        if (conn != null)
        {
            try
            {
                conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }       
    }
}
