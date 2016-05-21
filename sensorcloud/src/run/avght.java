package run;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class avght
 */
@WebServlet("/avght")
public class avght extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public avght() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		String json ="";
		PrintWriter out = response.getWriter();
		List <avghightemp> lc= new ArrayList<avghightemp>();
		HttpSession session = request.getSession(true);
       String user=session.getAttribute("username").toString();
       //String user = "root";
		//String user="root";
		Connection con1 = null;  
		//Connection con2 = null; 
		//Connection con3 = null; 
		Connection con4 = null; 
		Connection con5 = null;
		
		
		try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		 System.out.println("Entered the try");
		
		 con1 = (Connection) DriverManager.getConnection("jdbc:mysql://avghightemp.cjdsel9fsb9g.us-west-1.rds.amazonaws.com:3306/avghightemp","sharat","abcdxyz123");	
		
		 con4= (Connection) DriverManager.getConnection("jdbc:mysql://avghightemp.cjdsel9fsb9g.us-west-1.rds.amazonaws.com:3306/avghightemp","sharat","abcdxyz123");
		 con5= (Connection) DriverManager.getConnection("jdbc:mysql://photosensor.cjdsel9fsb9g.us-west-1.rds.amazonaws.com:3306/photosensor","sharat","abcdxyz123");
		 
	} catch(Exception e) {
		e.printStackTrace();
	}
		Statement stmt1 = null;
		Statement stmt2 = null;
		Statement stmt3 = null;
		Statement stmt4 = null;
		Statement stmt5 = null;
		Statement  sensorstmt = null;
		String sensorname="";
		String city="";
		String type="";
		try {
			
		String sensorquery = "select * from tempusersensor where userid="+"'"+user+"'";
		System.out.println(sensorquery);
		sensorstmt = (Statement) con1.createStatement();
		ResultSet sensorsrs = sensorstmt.executeQuery(sensorquery);
		sensorsrs.next();
		 sensorname= sensorsrs.getString(2);
		 city= sensorsrs.getString(3);
		 type= sensorsrs.getString(4);
		}catch (SQLException ex ) {
			System.out.println(ex);  
			
		} 
			
	
		
		String query2 = "select * from avghightemp where id="+"'"+sensorname+"'";
		String query3 = "select * from avglowtemp where id="+"'"+sensorname+"'";
		String query4 = "select * from avgrain where id="+"'"+sensorname+"'";
		String query5 = "select * from photosensor where id="+"'"+sensorname+"'";

		String ahJan = "";
		String ahFeb = "";
		String ahMarch ="";		
		String ahApril = "";
		String ahMay = "";
		String ahJune = "";	
		String ahJuly = "";
		String ahAug = "";
		String ahSept = "";
		String ahOct = "";
		String ahNov = "";
		String ahDec = "";
		//
		String alJan = "";
		String alFeb = "";
		String alMarch = "";		
		String alApril = "";
		String alMay = "";
		String alJune = "";
		String alJuly = "";
		String alAug = "";
		String alSept = "";
		String alOct = "";
		String alNov = "";
		String alDec = "";
		//
		String arJan = "";
		String arFeb = "";
		String arMarch = "";		
		String arApril = "";
		String arMay = "";
		String arJune = "";
		String arJuly = "";
		String arAug ="";
		String arSept = "";
		String arOct ="";
		String arNov = "";
		String arDec = "";
		//
		String asJan = "";
		String asFeb ="";
		String asMarch = "";
		
		String asApril = "";
		String asMay = "";
		String asJune ="";
		
		String asJuly = "";
		String asAug ="";
		String asSept = "";
		String asOct ="";
		String asNov = "";
		String asDec = "";
		try {
			
			if(type.contains("1"))
				
			{
				
				stmt2 = (Statement) con1.createStatement();
				ResultSet rs2 = stmt2.executeQuery(query2);
				rs2.next();
					 ahJan = rs2.getString("Jan");
					 ahFeb = rs2.getString("Feb");
					 ahMarch = rs2.getString("March");
					
					 ahApril = rs2.getString("April");
					 ahMay = rs2.getString("May");
					 ahJune = rs2.getString("June");
					
					 ahJuly = rs2.getString("July");
					 ahAug = rs2.getString("Aug");
					 ahSept = rs2.getString("Sept");
					 ahOct = rs2.getString("Oct");
					 ahNov = rs2.getString("Nov");
					 ahDec = rs2.getString("Dece");
					
					stmt3 = (Statement) con1.createStatement();
					ResultSet rs3 = stmt3.executeQuery(query3);
					rs3.next();
						 alJan = rs3.getString("Jan");
						 alFeb = rs3.getString("Feb");
						 alMarch = rs3.getString("March");
						
						 alApril = rs3.getString("April");
						 alMay = rs3.getString("May");
						 alJune = rs3.getString("June");
						
						 alJuly = rs3.getString("July");
						 alAug = rs3.getString("Aug");
						 alSept = rs3.getString("Sept");
						 alOct = rs3.getString("Oct");
						 alNov = rs3.getString("Nov");
						 alDec = rs3.getString("Dece");
			}
			if(type.contains("2"))
			{
				stmt4 = (Statement) con4.createStatement();
				ResultSet rs4 = stmt4.executeQuery(query4);
					rs4.next();
					 arJan = rs4.getString("Jan");
					 arFeb = rs4.getString("Feb");
					 arMarch = rs4.getString("March");
					
					 arApril = rs4.getString("April");
					 arMay = rs4.getString("May");
					 arJune = rs4.getString("June");
					
					 arJuly = rs4.getString("July");
					 arAug = rs4.getString("Aug");
					 arSept = rs4.getString("Sept");
					 arOct = rs4.getString("Oct");
					 arNov = rs4.getString("Nov");
					 arDec = rs4.getString("Dece");
			}
			if(type.contains("3"))
			{
				stmt5 = (Statement) con5.createStatement();
				ResultSet rs5 = stmt5.executeQuery(query5);
				rs5.next();
					 asJan = rs5.getString("Jan");
					 asFeb = rs5.getString("Feb");
					 asMarch = rs5.getString("March");
					
					 asApril = rs5.getString("April");
					 asMay = rs5.getString("May");
					 asJune = rs5.getString("June");
					
					 asJuly = rs5.getString("July");
					 asAug = rs5.getString("Aug");
					 asSept = rs5.getString("Sept");
					 asOct = rs5.getString("Oct");
					 asNov = rs5.getString("Nov");
					 asDec = rs5.getString("Dece");
			}				
				avghightemp avghtemp = new avghightemp(sensorname, ahJan,ahFeb,ahMarch,ahApril,ahMay,ahJune,ahJuly,ahAug,ahSept,ahOct,ahNov,ahDec,
				alJan,alFeb,alMarch,alApril,alMay,alJune,alJuly,alAug,alSept,alOct,alNov,alDec,
				arJan,arFeb,arMarch,arApril,arMay,arJune,arJuly,arAug,arSept,arOct,arNov,arDec,
				asJan,asFeb,asMarch,asApril,asMay,asJune,asJuly,asAug,asSept,asOct,asNov,asDec);
			    lc.add(avghtemp);
			    
			Gson gson = new Gson();
			 json = gson.toJson(lc);
			System.out.println("Json string:"+json);
			
		} catch (SQLException ex ) {
			System.out.println(ex);  
			System.exit(1); 
		} finally {
			System.out.println("finish");
			out.println(json);
			
		}
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		 
		
		
	}

}
