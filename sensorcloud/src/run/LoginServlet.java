package run;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;  
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;
public class LoginServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;  
	  
    public void doPost(HttpServletRequest request, HttpServletResponse response)    
            throws ServletException, IOException {    
  
        response.setContentType("text/html");    
        PrintWriter out = response.getWriter();   
        PreparedStatement pst = null;  
        ResultSet rs = null;  
        Connection conn = null;
        boolean status = false;
          
        String n=request.getParameter("username");    
        String p=request.getParameter("userpass");   
        HttpSession session = request.getSession(true);  
        if(session!=null)  
        session.setAttribute("username", n); 
        try {
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    		conn= DriverManager.getConnection("jdbc:mysql://avghightemp.cjdsel9fsb9g.us-west-1.rds.amazonaws.com:3306/avghightemp","sharat","abcdxyz123");
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
        try {
			pst = conn.prepareStatement("select * from userinfo where userid=? and pass=?");
			pst.setString(1, n);  
	        pst.setString(2, p); 
	        
	        rs = pst.executeQuery();
	        status = rs.next();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
         
        
        
        if(!status)
        {
        	out.print("<p style=\"color:red\">Sorry username or password error</p>");    
            RequestDispatcher rd=request.getRequestDispatcher("login.html");    
            rd.include(request,response); 
        }
        else {
        	 RequestDispatcher rd=request.getRequestDispatcher("menu.html");    
             rd.forward(request,response);
        }
        	
        }

        
    
    
}
