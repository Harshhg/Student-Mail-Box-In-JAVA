/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Harsh Gupta
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         String email = request.getParameter("email");
  String pass = request.getParameter("password");
  String msg = "Invalid";
  String db_email = "", db_pass = "";
     try {
    
   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  
   Connection con= DriverManager.getConnection("jdbc:odbc:mydsn");  
  java.sql.Statement st=  con.createStatement();  
  String query = "SELECT * from java where Email = '"+email+"'";
ResultSet rs = st.executeQuery(query);
   if(rs.next())
         { 
  db_email = rs.getString("Email");
  db_pass = rs.getString("Password");
         }
 if(email.equals(db_email) && pass.equals(db_pass))
    {
        HttpSession session=request.getSession();  
        session.setAttribute("user",db_email);  
     request.setAttribute("email", email);
     RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");  
        rd.forward(request, response);
     //response.sendRedirect("home.jsp");
     
    }
 else
 {
    
      request.getRequestDispatcher("/login.jsp").include(request, response);  
        out.print("<script> alert('Invalid Credentials!') </script>");   
  
 }

         
   

     
    
    
    con.close();
   } 
   catch (Exception e) 
   {
    out.print(e);
   
   }
 
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
