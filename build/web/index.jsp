<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Student's Site</title>
<meta charset="utf-8">
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
 <link rel="stylesheet" href="css/tablestyle.css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/cufon-replace.js"></script>
<script type="text/javascript" src="js/Myriad_Pro_300.font.js"></script>
<script type="text/javascript" src="js/Myriad_Pro_400.font.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<!--[if lt IE 7]>
<link rel="stylesheet" href="css/ie6.css" type="text/css" media="screen">
<script type="text/javascript" src="js/ie_png.js"></script>
<script type="text/javascript">ie_png.fix('.png, footer, header nav ul li a, .nav-bg, .list li img');</script>
<![endif]-->
<!--[if lt IE 9]><script type="text/javascript" src="js/html5.js"></script><![endif]-->
</head>


  <% 
  int inbox_count =0;
  int sent_count = 0;
  int groupsent_count =0;
  String name = "";
  String user = null;
  user=(String)session.getAttribute("user");
   if(user==null)
             {
             response.sendRedirect("login.jsp");
             }
   else
             {
  
     
 try {
  
   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  
   Connection con= DriverManager.getConnection("jdbc:odbc:mydsn");  
  java.sql.Statement st=  con.createStatement();  
  String q = "SELECT Name from java where email = '"+user+"' ";
  ResultSet rs1 = st.executeQuery(q);
  if(rs1.next())
     name = rs1.getString("Name");
  String query = "SELECT * from email where receiver = '"+user+"' ";
  ResultSet inboxmail = st.executeQuery(query);
  while(inboxmail.next())
           {inbox_count++;}
       
  String query1 = "SELECT * from email where sender = '"+user+"' and groupmail = '0'";
  ResultSet sentmail = st.executeQuery(query1);
  while(sentmail.next())
           {sent_count++;}
  
  String query2 = "SELECT * from email where sender = '"+user+"' and groupmail = '1'";
  ResultSet groupsentmail = st.executeQuery(query2);
  while(groupsentmail.next())
           {groupsent_count++;}
  
  
  
  con.close(); 
 }
 catch(Exception e)
                 {
     
 }

 
   }
  






%>
<body id="page1">
<!-- START PAGE SOURCE -->
<div class="wrap">
  <header>
    <div class="container">
      <h1><a href="#">Student's site</a></h1>
      <nav>
        <ul>
          <li class="current"><a href="index.jsp" class="m1">Home Page</a></li>
          <li><a href="inbox.jsp" class="m2">Inbox</a></li>
          <li><a href="sentmail.jsp" class="m3">Sent Mail</a></li>
          <li><a href="groupsentmail.jsp" class="m4">Group Sent</a></li>
          <li class="last"><a href="logout" class="m5">LogOut</a></li>
        </ul>
      </nav>
      <form action="#" id="search-form">
         
          <div class="rowElem">
              <font style="color :  #000; font-size: 20px; font-family: monospace ; margin-bottom: 6px; background: url(images/divider1.gif) repeat-x left bottom;  ">  
                  Welcome </font>
                  <font style="color :  #990099; font-size: 20px;font-family: monospace ;  margin-bottom: 6px; background: url(images/divider1.gif) repeat-x left bottom; ">   <% out.print(name); %>  </font> 
                  
                  
          </div>
      
      </form>
    </div>
  </header>
  <div class="container">
    <aside>
        <div><marquee behavior=" "><h3 style="color:green">
                   <% out.print(name); %> 
               
               </h3></marquee></div>
      <h3>Categories</h3>
      <ul class="categories">
        <li><span><a href="sendmailto.jsp"  id = " "> Compose </a></span></li>
		<li><span><a href="inbox.jsp">Inbox (<%=inbox_count%>)</a></span></li>
        <li><span><a href="#">Starred</a></span></li>
        <li><span><a href="#">Draft</a></span></li>
        <li><span><a href="sentmail.jsp">Sent Mail (<%=sent_count%>)</a></span></li>
        <li><span><a href="groupsentmail.jsp">Group Sent (<%=groupsent_count%>)</a></span></li>
         
      </ul>
      <form action="#" id="newsletter-form">
         
      </form>
      
    </aside>
    <section id="content">
        <div id="banner">
        <h2>Student's Mail Box<span> <br><span>Interconnecting Peoples..</span></span></h2>
      </div>
      <div class="inside"> 
         
        
          
   		
  
      
</div>
      
      
  
    
 
</div>
		  
		  
		  
		  
		   
       
      </div>
    </section>
  </div>
</div>
<footer>
  <div class="footerlink">
    <p class="lf">Copyright &copy; 2018 <a href="index.jsp">Student's Site</a> - All Rights Reserved</p>
   <p class="rf">Developed by <a href="http://www.harsh-gupta.tk" target="blank">Harsh Gupta</a></p>
    <div style="clear:both;"></div>
  </div>
</footer>
<script type="text/javascript"> Cufon.now(); </script>
<!-- END PAGE SOURCE -->
</body>
</html>
