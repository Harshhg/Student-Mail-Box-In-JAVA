/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author Harsh Gupta
 */
public class Register extends HttpServlet {

     
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
       
        response.setContentType("text/html");
  PrintWriter out = response.getWriter();
  String name = request.getParameter("fullname");
  String email = request.getParameter("email");
  String pass = request.getParameter("password");
String dd = (request.getParameter("DOBdd"));
 String mm = (request.getParameter("DOBmm"));
String yy = request.getParameter("DOByy");
  String gender = request.getParameter("gender");
  String stream = request.getParameter("stream");
   String dept = request.getParameter("department");
    String sem = request.getParameter("semester");
 
    String date = dd+"-"+mm+"-"+yy;
    
   /* 
  // validate given input
  /*if (name.isEmpty() || addr.isEmpty() || age.isEmpty() || qual.isEmpty() || percent.isEmpty() || year.isEmpty()) {
   RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
   out.println("<font color=red>Please fill all the fields</font>");
   rd.include(request, response);
  } else {
   // inserting data into mysql database 
   // create a test database and student table before running this to create table
   //create table student(name varchar(100), userName varchar(100), pass varchar(100), addr varchar(100), age int, qual varchar(100), percent varchar(100), year varchar(100));*/
   try {
    
   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  
   Connection con=DriverManager.getConnection("jdbc:odbc:mydsn");  
  java.sql.Statement st=  con.createStatement();  
  
  String q  = "SELECT * from java where Email = '"+email+"'";
ResultSet rs = st.executeQuery(q);
   if(rs.next())
         { 
             request.getRequestDispatcher("/login.jsp").include(request, response);  
            out.print("<script> alert('Already Registered! Please Login..') </script>");  
         }
   
   else{
String query = ("insert into java values('"+name+"' , '"+email+"', '"+pass+"', '"+date+"', '"+gender+"', '"+stream+"', '"+dept+"' , '"+sem+"');");
            
st.executeUpdate(query); 
   
 request.getRequestDispatcher("/login.jsp").include(request, response);  
        out.print("<script> alert('Successfully Registered!') </script>");   
   } 
   
    
    
    con.close();
   } 
   catch (Exception e) 
   {
    out.print(e);
   
   }
   
       
        
      
        
        
        
        
    }}
