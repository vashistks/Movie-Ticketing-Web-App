package user;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dto.*;
import dao.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import services.RainbowServices;

public class userBookingAction extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        try {
            RainbowServices services = new RainbowServices();
            HttpSession session = request.getSession();
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            Customer customer = (Customer) session.getAttribute("customer");
            int showId = Integer.parseInt(""+session.getAttribute("showId"));
            int bookingId = services.bookTickets(customer, showId, quantity);
            if( bookingId != -1)
            {
                session.setAttribute("bookingId", bookingId);
                RequestDispatcher dispatcher = request.getRequestDispatcher("bookingSuccess.jsp");
                dispatcher.forward(request, response);
            }
            else
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("bookingPage.jsp");
                dispatcher.forward(request, response);
            }
        } catch(Exception e) {e.printStackTrace();}
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
