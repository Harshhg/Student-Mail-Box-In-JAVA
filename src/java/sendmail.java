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
@WebServlet(name = "sendmail", urlPatterns = {"/sendmail"})
public class sendmail extends HttpServlet {
String name = "";
String dept = "";
String sem = "";
String stream = "";
String msg = "";
String receiveremail = "";
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
        int count =0;
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        receiveremail = request.getParameter("rmail");
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
    String q  = "SELECT Email from java where Email = '"+receiveremail+"'";
    rs = st.executeQuery(q);
        while(rs.next())
         { count++;}
         if(count<1)
            {request.getRequestDispatcher("compose.jsp").include(request, response);  
                out.print("<script> alert('Email address Not Found ! ') </script>"); }
         
        else
        {
        
         String query1 = "INSERT into email VALUES('"+user+"', '"+receiveremail+"', '"+sub+"' , '"+msg +"' , '"+name+"', '"+dept+"', '"+stream+"' , '"+sem+"' ,'0')";
         st.executeUpdate(query1);
     
      request.getRequestDispatcher("index.jsp").include(request, response);  
        out.print("<script> alert('Message Sent') </script>"); 
        }
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
