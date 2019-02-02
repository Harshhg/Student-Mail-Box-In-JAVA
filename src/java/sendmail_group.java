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
@WebServlet(name = "sendmail_group", urlPatterns = {"/sendmail_group"})
public class sendmail_group extends HttpServlet {
String name = "";
String dept = "";
String sem = "";
String stream = "";
String msg = "";
String tostream = "";
String todept = "";
String tosem = "";
String rmail = "";
 String sub = "";
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        tostream = request.getParameter("stream");
        todept = request.getParameter("department");
        tosem = request.getParameter("semester");
        msg = request.getParameter("message");
           sub = request.getParameter("subject");
        try {
         HttpSession session=request.getSession(); 
            String user = (String)session.getAttribute("user");
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  
        Connection con= DriverManager.getConnection("jdbc:odbc:mydsn");  
         java.sql.Statement st=  con.createStatement();  
         String query = "SELECT * from java where Email = '"+user+"'";
        ResultSet rs = st.executeQuery(query);
        if(rs.next())
         { 
        name = rs.getString("Name");
        dept = rs.getString("Department");
        stream = rs.getString("Stream");
        sem = rs.getString("Semester");
         }
        String query1 = "SELECT * from java where Stream = '"+tostream+"' and Department = '"+todept+"' and Semester = '"+tosem+"'  ";
        ResultSet rs1 = st.executeQuery(query1);
        while(rs1.next())
         { 
            rmail = rs1.getString("Email"); 
         String query3 = "INSERT into email VALUES('"+user+"', '"+rmail+"','"+sub+"' , '"+msg +"' , '"+name+"', '"+dept+"', '"+stream+"' , '"+sem+"' , '1')";
            java.sql.Statement st1=  con.createStatement();
         st1.executeUpdate(query3);
             
        }
        
      request.getRequestDispatcher("index.jsp").include(request, response);  
         out.print("<script> alert('Group Message Sent') </script>"); 
        
        
    con.close();
        
             
        } catch(Exception e)            
        {out.close();
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
