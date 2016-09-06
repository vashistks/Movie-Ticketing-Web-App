package user;

import dto.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.RainbowServices;
public class userProfileAction extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try 
        {
            RainbowServices services = new RainbowServices();
            Customer oldCustomerInfo = (Customer) (request.getSession().getAttribute("customer"));
            RequestDispatcher dispatcher;
            if(request.getParameter("current_password").equals(oldCustomerInfo.getUserPassword()) == false)
            {
                System.out.println("Current password is wrong!");
                System.out.println("Compare the old: "+oldCustomerInfo.getUserPassword()+" with with the given current as: "+request.getParameter("current_password"));
                dispatcher = request.getRequestDispatcher("userSettingsPage.jsp");  
                dispatcher.forward(request, response);
            }
            else
            {
                Customer customer =  services.createCustomerObjectForUpdate(request);
                customer.setEmailId(oldCustomerInfo.getEmailId());
                customer = services.updateCustomer(customer);
                HttpSession session = request.getSession();
                session.setAttribute("customer", customer);
                dispatcher = request.getRequestDispatcher("index.jsp"); 
                dispatcher.forward(request, response);
            }
        } catch(Exception e){e.printStackTrace();}
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
