package run; 

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession; 
public class Logindao {

	public Logindao() {		
		// TODO Auto-generated constructor stub
	}
	public static boolean validate(String name, String pass,HttpServletRequest request) throws SQLException {          
        boolean status = false;    
        PreparedStatement pst = null;  
        ResultSet rs = null;  
  
       
        Connection conn = null;
        HttpSession session = request.getSession(true);
        String us=session.getAttribute("username").toString();

	try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn= DriverManager.getConnection("jdbc:mysql://userlogin.cjdsel9fsb9g.us-west-1.rds.amazonaws.com:3306/userlogin","sharat","abcdxyz123");
	} catch(Exception e) {
		e.printStackTrace();
	}
    pst = conn.prepareStatement("select * from userlogin where username=? and password=?");  
    pst.setString(1, name);  
    pst.setString(2, pass);  

    rs = pst.executeQuery();  
    status = rs.next();


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

return status;

}
}
