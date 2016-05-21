package run;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class sensoradd
 */
@WebServlet("/sensoradd")
public class sensoradd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public sensoradd() {
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


		System.out.println("hello");
		HttpSession session = request.getSession(true);
		String username=session.getAttribute("username").toString();
		String sensorname = request.getParameter("sensorname");
		String sensorgrp = request.getParameter("sensorgrp");
		String sensorcity = request.getParameter("city");

		String sensorparameter = request.getParameter("sensorparameter");
		PrintWriter out = response.getWriter(); 
		String type="";
		String [] typepara = null;
		typepara = sensorparameter.split(",");
		int len = typepara.length;
		int intcount =0;
		for(int i=0;i<len;i++)
        {
        	if(intcount==0)
        	{
        		type=typepara[i];
        		
        	}
        	if(intcount>0)
        	{
        		type+=","+typepara[i];
        	}
        	intcount++;
        }
		Document doc=null;
		if(sensorcity.contains("washington"))
		{
			 doc = Jsoup.connect("http://www.usclimatedata.com/climate/washington/district-of-columbia/united-states/usdc0001").get(); 

		}
		//Document doc1 = Jsoup.connect("http://www.usclimatedata.com/climate/washington/district-of-columbia/united-states/usdc0001").get(); 
		if(sensorcity.contains("omaha"))
		{
			 doc = Jsoup.connect("http://www.usclimatedata.com/climate/omaha/nebraska/united-states/usne0795").get(); 

		}
		//if(sensorcity.contains("boston"))
		//{
		//	 doc = Jsoup.connect("http://www.usclimatedata.com/climate/boston/massachusetts/united-states/usma0046").get(); 

		//}
		//Document doc2 = Jsoup.connect("http://www.usclimatedata.com/climate/omaha/nebraska/united-states/usne0795").get(); 
		//Document doc3 = Jsoup.connect("http://www.usclimatedata.com/climate/boston/massachusetts/united-states/usma0046").get(); 
		if(sensorcity.contains("San Jose"))
		{
			 doc = Jsoup.connect("http://www.usclimatedata.com/climate/san-jose/california/united-states/usca0993").get(); 

		}
		//Document doc3 = Jsoup.connect("http://www.usclimatedata.com/climate/san-jose/california/united-states/usca0993").get(); 

		Elements tableElements = doc.select("table");




		Elements tableRowElements = tableElements.select(":not(thead th)");
		//tableRowElements.remove(0);
		//tableRowElements.remove(1);
		//tableRowElements.remove(2);
		//tableRowElements.remove(3);
		System.out.println(tableRowElements.size());
		/*  for (int i = 0; i < tableRowElements.size(); i=i+3) {							//while(tableElements.select(":not (/table) "))
        	System.out.println("i:"+i); 		
        	Element row = tableRowElements.get(i);
                    Elements rowItems = row.select("td");
                    for (int j = 0; j < rowItems.size(); j++) {
                    	System.out.println(j);
                       System.out.println(rowItems.get(j).text());
                    }
                    System.out.println();
         }
		 */

		//switch statement begins

		int count = 0;
		int i;
		int j;
		int counter=0; 
		String[] temp1 = new String[21];
		String[] temp2 = new String[21];
		String[] temp3 = new String[6];
		String[] temp4 = new String[6];

		String ann_htemp = null, avg_ltemp = null, avg_temp = null,avg_annrain = null,city = null, longitude = null,latitude = null,time = null,zone = null;

		while (count <7){

			count ++; 
			switch(count){

			case 1 : i = 0;
			Element row = tableRowElements.get(i);
			Elements rowItems = row.select("td");
			for (j = 0; j < 21; j++) {

				temp1 [j]=(rowItems.get(j).text());
				System.out.println("temp1"+"["+j+ "]"+":" + temp1[j]);
			}
			counter =0;
			for (j = 29; j < 35; j++) {

				temp3 [counter]=(rowItems.get(j).text());
				System.out.println("temp3"+"["+counter+ "]"+":" + temp3[counter]);
				counter = counter+1;
			}
			System.out.println();	

			break;

			case 2: i = 54;
			row = tableRowElements.get(i);
			rowItems = row.select("td");
			for (j = 0; j < 21; j++) {

				temp2 [j]=(rowItems.get(j).text());
				System.out.println("temp2"+"["+j+ "]"+":" + temp1[j]);
			}
			counter =0;
			for (j = 29; j < 35; j++) {

				temp4 [counter]=(rowItems.get(j).text());
				System.out.println("temp4"+"["+counter+ "]"+":" + temp4[counter]);
				counter = counter + 1;
			}
			System.out.println();	
			System.out.println();	


			break;

			case 3: i = 108;
			j=1;
			row = tableRowElements.get(i);
			rowItems = row.select("td");

			ann_htemp=(rowItems.get(j).text());
			System.out.println("annual temp" + ann_htemp);

			System.out.println();	

			break;

			case 4:i = 111;
			j=1;
			row = tableRowElements.get(i);
			rowItems = row.select("td");

			avg_ltemp=(rowItems.get(j).text());
			System.out.println("avg low temp" + avg_ltemp);

			System.out.println();	

			break;

			case 5: i = 114;
			j=1;
			row = tableRowElements.get(i);
			rowItems = row.select("td");

			avg_temp=(rowItems.get(j).text());
			System.out.println("avg temp"+avg_temp);

			System.out.println();	

			break;

			case 6 : i = 117;
			j=1;

			row = tableRowElements.get(i);
			rowItems = row.select("td");

			avg_annrain=(rowItems.get(j).text());
			System.out.println("avg annual rain" + avg_annrain);

			System.out.println();	

			break;
			
			/*case 7 : i = 708;
			j = 0;

			row = tableRowElements.get(i);
			rowItems = row.select("td");

			city =(rowItems.get(j).text());
			System.out.println("city"+city);

			System.out.println();	

			break;

			case 8 : i = 714;
			j = 0;

			row = tableRowElements.get(i);
			rowItems = row.select("td");

			longitude=(rowItems.get(j).text());
			System.out.println("longitude" + longitude);

			System.out.println();	

			break;

			case 9 : i = 717;
			j=0;

			row = tableRowElements.get(i);
			rowItems = row.select("td");

			latitude=(rowItems.get(j).text());
			System.out.println("latitude" + latitude);

			System.out.println();	

			break;

			case 10 : i = 729;
			j=0;

			row = tableRowElements.get(i);
			rowItems = row.select("td");

			time=(rowItems.get(j).text());
			System.out.println("time" + time);

			System.out.println();	

			break;

			case 11 : i = 744;
			j = 0;

			row = tableRowElements.get(i);
			rowItems = row.select("td");

			zone=(rowItems.get(j).text());
			System.out.println("zone" + zone);

			System.out.println();	

			break;
			*/

			default : System.out.println();
			break;






			} //switch loop


		} //while loop     



		//invoke jdbc 
		// initialise 
		String ah_jan =temp1[1] ;
		String ah_feb = temp1[2] ;
		String ah_mar =temp1[3] ;
		String ah_apr = temp1[4] ;
		String ah_may = temp1[5]  ;
		String ah_june = temp1[6] ;
		String ah_july = temp2[1] ;
		String ah_aug = temp2[2] ;
		String ah_sep = temp2[3] ;
		String ah_oct = temp2[4] ;
		String ah_nov = temp2[5] ;
		String ah_dece = temp2[6] ;

		String al_jan = temp1[8]  ;
		String al_feb= temp1[9]  ;
		String al_mar = temp1[10] ;
		String al_apr = temp1[11] ;
		String al_may = temp1[12] ;
		String al_june = temp1[13] ;
		String al_july = temp2[8] ;
		String al_aug = temp2[9] ;
		String al_sep= temp2[10] ;
		String al_oct = temp2[11];
		String al_nov = temp2[12];
		String al_dece = temp2[13];

		String ap_jan = temp1[15]  ;
		String ap_feb = temp1[16] ;
		String ap_mar = temp1[17] ;
		String ap_apr = temp1[18] ;
		String ap_may = temp1[19] ;
		String ap_june = temp1[20] ;
		String ap_july = temp2[15] ;
		String ap_aug = temp2[16] ;
		String ap_sep = temp2[17] ;
		String ap_oct = temp2[18] ;
		String ap_nov = temp2[19] ;
		String ap_dece = temp2[20] ;

		String as_jan = temp3[0]  ;
		String as_feb = temp3[1] ;
		String as_mar = temp3[2] ;
		String as_apr = temp3[3] ;
		String as_may = temp3[4] ;
		String as_june = temp3[5] ;
		String as_july = temp4[0] ;
		String as_aug = temp4[1] ;
		String as_sep = temp4[2] ;
		String as_oct = temp4[3] ;
		String as_nov = temp4[4] ;
		String as_dece = temp4[5] ;

		System.out.println("trying to connect to db");
		Connection con = null;  
		Connection con5 = null;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Entered the try");

			con= (Connection) DriverManager.getConnection("jdbc:mysql://avghightemp.cjdsel9fsb9g.us-west-1.rds.amazonaws.com:3306/avghightemp","sharat","abcdxyz123");
			/* con2= (Connection) DriverManager.getConnection("jdbc:mysql://avglowtemp.cjdsel9fsb9g.us-west-1.rds.amazonaws.com:3306/avglowtemp","sharat","abcdxyz123");

			 con3= (Connection) DriverManager.getConnection("jdbc:mysql://avgrain.cjdsel9fsb9g.us-west-1.rds.amazonaws.com:3306/avgrain","sharat","abcdxyz123");
			 con4= (Connection) DriverManager.getConnection("jdbc:mysql://weathergeneral.cjdsel9fsb9g.us-west-1.rds.amazonaws.com:3306/weathergeneral","sharat","abcdxyz123");
			 */
			 con5 = (Connection) DriverManager.getConnection("jdbc:mysql://photosensor.cjdsel9fsb9g.us-west-1.rds.amazonaws.com:3306/photosensor","sharat","abcdxyz123");
			 
		} catch(Exception e) {
			e.printStackTrace();
		}

		try {  


			//java.sql.Statement statement = con.createStatement();  
			//	mytest.submitData();



			// create the mysql insert preparedstatement
			System.out.println(typepara.length);



			System.out.println("inserting data");
			String query = " INSERT INTO usersensor (userid,sensorname,city,type)"
					+ " values (?,?, ?,?)";


			PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
			preparedStmt.setString (1, username);
			preparedStmt.setString (2, sensorname);
			preparedStmt.setString (3, sensorcity);
			preparedStmt.setString (4, type);
			preparedStmt.execute();

			
					System.out.println("hello temp");

					System.out.println("inserting data");
					query = " INSERT INTO avghightemp (id,Jan,Feb,March,April,May,June,July,Aug,Sept,Oct,Nov,Dece)"
							+ " values (?,?, ?, ?, ?, ?,?,?,?,?,?,?,?)";


					preparedStmt = (PreparedStatement) con.prepareStatement(query);
					preparedStmt.setString (1, sensorname);
					preparedStmt.setString (2, ah_jan);
					preparedStmt.setString (3, ah_feb);
					preparedStmt.setString (4, ah_mar);
					preparedStmt.setString (5, ah_apr);
					preparedStmt.setString (6, ah_may);
					preparedStmt.setString (7, ah_june);
					preparedStmt.setString (8, ah_july);
					preparedStmt.setString (9, ah_aug);
					preparedStmt.setString (10, ah_sep);
					preparedStmt.setString (11, ah_oct);
					preparedStmt.setString (12, ah_nov);
					preparedStmt.setString (13, ah_dece);
					// execute the preparedstatement
					preparedStmt.execute();

					//String command = " VALUES (" + fname + "," + mname + "," + lname + "," + fmob + "," + smob + "," + tmob + "," + code + "," + mob + "," + landline + "," + email + ");";
					//System.out.println("")
					//statement.executeUpdate(command);


					String query2 = " INSERT INTO avglowtemp (id,Jan,Feb,March,April,May,June,July,Aug,Sept,Oct,Nov,Dece)"
							+ " values (?,?, ?, ?, ?, ?,?,?,?,?,?,?,?)";

					// create the mysql insert preparedstatement
					PreparedStatement preparedStmt2 = (PreparedStatement) con.prepareStatement(query2);
					preparedStmt2.setString (1, sensorname);
					preparedStmt2.setString (2, al_jan);
					preparedStmt2.setString (3, al_feb);
					preparedStmt2.setString (4, al_mar);
					preparedStmt2.setString (5, al_apr);
					preparedStmt2.setString (6, al_may);
					preparedStmt2.setString (7, al_june);
					preparedStmt2.setString (8, al_july);
					preparedStmt2.setString (9, al_aug);
					preparedStmt2.setString (10, al_sep);
					preparedStmt2.setString (11, al_oct);
					preparedStmt2.setString (12, al_nov);
					preparedStmt2.setString (13, al_dece);
					// execute the preparedstatement
					preparedStmt2.execute();




					System.out.println("hello rain");

					String query3 = " INSERT INTO avgrain (id,Jan,Feb,March,April,May,June,July,Aug,Sept,Oct,Nov,Dece)"
							+ " values (?,?, ?, ?, ?, ?,?,?,?,?,?,?,?)";

					// create the mysql insert preparedstatement
					PreparedStatement preparedStmt3 = (PreparedStatement) con.prepareStatement(query3);
					preparedStmt3.setString (1, sensorname);
					preparedStmt3.setString (2, ap_jan);
					preparedStmt3.setString (3, ap_feb);
					preparedStmt3.setString (4, ap_mar);
					preparedStmt3.setString (5, ap_apr);
					preparedStmt3.setString (6, ap_may);
					preparedStmt3.setString (7, ap_june);
					preparedStmt3.setString (8, ap_july);
					preparedStmt3.setString (9, ap_aug);
					preparedStmt3.setString (10, ap_sep);
					preparedStmt3.setString (11, ap_oct);
					preparedStmt3.setString (12, ap_nov);
					preparedStmt3.setString (13, ap_dece);
					// execute the preparedstatement
					preparedStmt3.execute();




				
					System.out.println("hello photosensor");

					String query5 = " INSERT INTO photosensor (id,Jan,Feb,March,April,May,June,July,Aug,Sept,Oct,Nov,Dece)"
							+ " values (?,?, ?, ?, ?, ?,?,?,?,?,?,?,?)";

					// create the mysql insert preparedstatement
					PreparedStatement preparedStmt5 = (PreparedStatement) con5.prepareStatement(query5);
					preparedStmt5.setString (1, sensorname);
					preparedStmt5.setString (2, as_jan);
					preparedStmt5.setString (3, as_feb);
					preparedStmt5.setString (4, as_mar);
					preparedStmt5.setString (5, as_apr);
					preparedStmt5.setString (6, as_may);
					preparedStmt5.setString (7, as_june);
					preparedStmt5.setString (8, as_july);
					preparedStmt5.setString (9, as_aug);
					preparedStmt5.setString (10, as_sep);
					preparedStmt5.setString (11, as_oct);
					preparedStmt5.setString (12, as_nov);
					preparedStmt5.setString (13, as_dece);
					// execute the preparedstatement
					boolean check = preparedStmt5.execute();
					
					if(check){
						
							out.print("<p style=\"color:green\">Sensor added successfully</p>");  	
							RequestDispatcher rd=request.getRequestDispatcher("menu.html");    
							rd.include(request,response); 
						}
					



				

				String query4 = " INSERT INTO weathergeneral (id,avghtemp,avgltemp,avgtemp,avgrain)"
						+ " values (?,?, ?, ?, ?)";

				// create the mysql insert preparedstatement
				PreparedStatement preparedStmt4 = (PreparedStatement) con.prepareStatement(query4);
				preparedStmt4.setString (1, sensorname);
				preparedStmt4.setString (2, ann_htemp);
				preparedStmt4.setString (3, avg_ltemp);
				preparedStmt4.setString (4, avg_temp);
				preparedStmt4.setString (5, avg_annrain);
				
				/*
				preparedStmt4.setString (6, city);
				preparedStmt4.setString (7, longitude);
				preparedStmt4.setString (8, latitude);
				preparedStmt4.setString (9, time);
				preparedStmt4.setString(10,zone );
				preparedStmt4.execute();
				*/
				RequestDispatcher rd=request.getRequestDispatcher("menu.html");    
	            rd.include(request,response); 

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				
			}




	


		

	 


		

		

	


	}











}


