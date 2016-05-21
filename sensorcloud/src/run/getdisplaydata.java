package run;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

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
 * Servlet implementation class getdisplaydata
 */
@WebServlet("/getdisplaydata")
public class getdisplaydata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getdisplaydata() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		Connection conn = null;
		PreparedStatement pst = null;  
		PreparedStatement pstdel = null;  
		ResultSet rs = null;  
	
		response.setContentType("text/html");    
        PrintWriter out = response.getWriter();        
       
		String driver = "com.mysql.jdbc.Driver"; 
        String sensorid =request.getParameter("submit");
        String []typeparameter=request.getParameterValues("sen");
        String city = request.getParameter("city");
        String sensorname=request.getParameter("labelName");
        HttpSession session = request.getSession(true);
        String user=session.getAttribute("username").toString();
        String type="0";
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
			//delete before insert
			try {
				pstdel = (PreparedStatement) conn  
	     	            .prepareStatement("delete from tempusersensor where userid=?");
				pstdel.setString(1, user);
				//pstdel.setString(2, sensorname);
				int delj = pstdel.executeUpdate();
				//end
				 }catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
			 try {
			pst = (PreparedStatement) conn  
     	            .prepareStatement("insert into  tempusersensor values(?,?,?,?)");  
			pst.setString(1, user);
			 pst.setString(2, sensorname);
	     	  pst.setString(3, city);
	     	 pst.setString(4, type);
	     	  int j=pst.executeUpdate();
	     	  if(j>=1)
	     	  {
	     		  
	     		// out.print("<p style=\"color:green\"> successfully</p>");  	
					RequestDispatcher rd=request.getRequestDispatcher("show.html");    
					rd.include(request,response); 
	     	  }
			 }catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        } catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
	
	


       

	

        
	
	
	


