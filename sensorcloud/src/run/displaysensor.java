package run;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class displaysensor
 */
@WebServlet("/displaysensor")
public class displaysensor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public displaysensor() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.getWriter().append("successfully deleted");
		RequestDispatcher rd=request.getRequestDispatcher("menu.html");    
		rd.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		boolean status = false;  
		Connection conn = null;  
		PreparedStatement pst=null;
		PrintWriter out = response.getWriter(); 
		PreparedStatement pstdelete = null;  
		ResultSet rs = null;  
		String driver = "com.mysql.jdbc.Driver"; 
		String sensorid =request.getParameter("submit");
		String []typeparameter=request.getParameterValues("sen");
		String city = request.getParameter("city");
		String sensorname=request.getParameter("labelName");
		HttpSession session = request.getSession(true);
		String user=session.getAttribute("username").toString();
		String type="0";
		Statement stmt = null;
		int len= typeparameter.length;
		int count =0;

		if(request.getParameterValues("sen")!=null)
		{
			for(int i=0;i<len;i++)
			{
				if(count==0)
				{
					type=typeparameter[i];

				}
				if(count>0)
				{
					type+=","+typeparameter[i];
				}
				count++;
			}
		}
		try{

			Class.forName(driver).newInstance();  
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://avghightemp.cjdsel9fsb9g.us-west-1.rds.amazonaws.com:3306/avghightemp","sharat","abcdxyz123"); 


			response.setContentType("text/html");    
			if (sensorid.contains("Modify"))
			{

				try {

					pst = (PreparedStatement) conn  
							.prepareStatement("update usersensor set type=? ,city=? where sensorname=? and userid=?");  
					pst.setString(1, type);
					pst.setString(2, city);
					pst.setString(3, sensorname);
					pst.setString(4, user);
					int j=pst.executeUpdate();

					if(j>=1)
					{
						out.print("<p style=\"color:green\">Modified successfully</p>");  	
						RequestDispatcher rd=request.getRequestDispatcher("menu.html");    
						rd.include(request,response); 
					}
					else
					{
						out.print("<p style=\"color:red\">Error in modification</p>");  	
						

					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			if (sensorid.contains("Delete"))
			{

				try {
					pstdelete = (PreparedStatement) conn  
							.prepareStatement("delete from usersensor where sensorname=? and userid=?");  
					pstdelete.setString(1, sensorname);
					pstdelete.setString(2, user);

					int del =pstdelete.executeUpdate();

					if(del>=1)
					{
						out.print("<p style=\"color:green\">Deleted successfully</p>");  	
						RequestDispatcher rd=request.getRequestDispatcher("menu.html");    
						rd.include(request,response); 
					}
					else
					{
						out.print("<p style=\"color:red\">Error in Deletion</p>");  	
						RequestDispatcher rd=request.getRequestDispatcher("menu.html");    
						rd.include(request,response); 

					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{

					if (pstdelete != null) {  
						try {  
							pstdelete.close();  
						} catch (SQLException e) {  
							e.printStackTrace();  
						}  
					} 
				}

			}

		}

		catch (Exception e) {  
			System.out.println(e); 
		}finally{
			if (conn != null) {  
				try {  
					conn.close();  
				} catch (SQLException e) {  
					e.printStackTrace();  
				}  
			}  
			if (pst != null) {  
				try {  
					pst.close();  
				} catch (SQLException e) {  
					e.printStackTrace();  
				}  
			}  
			if (rs != null) {  
				try {  
					rs.close();  
				} catch (SQLException e) {  
					e.printStackTrace();  
				}  
			}  
		}




	}









}


