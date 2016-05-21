package run;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class signup
 */
@WebServlet("/signup")
public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();  
	String un = request.getParameter("user");
		String pwd = request.getParameter("pass");
		
		
		Connection con = null;   
		try {
			//connect to main db 
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//sensor data sql db
			con= DriverManager.getConnection("jdbc:mysql://avghightemp.cjdsel9fsb9g.us-west-1.rds.amazonaws.com:3306/avghightemp","sharat","abcdxyz123");
		} catch(Exception e) {
			e.printStackTrace();
		}
		try
		{
			String query1 = " INSERT INTO userinfo (userid,pass)" + " values (?, ?)";
			PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query1);
			preparedStmt.setString (1, un);
			preparedStmt.setString (2, pwd);
			int j= preparedStmt.executeUpdate();
			
			if(j>=1)
			{
			RequestDispatcher rd=request.getRequestDispatcher("index.html");    
            rd.include(request,response); 
			}
			else
			{
				out.print("<p style=\"color:red\">Error in Creating user</p>"); 
				RequestDispatcher rd=request.getRequestDispatcher("signup.html");    
	            rd.include(request,response);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("finish");
		}
	}

}
