<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js">
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sensor Status</title>
 <style type="text/css">
 
 <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">

    <!-- Custom Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css" type="text/css">
	<link rel="stylesheet" href="css/creative.css" type="text/css">
        <link rel="stylesheet" type="text/css" href="css/demo.css" />
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <link rel="stylesheet" type="text/css" href="css/animate-custom.css" />
<link href='http://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
<body style="background-image:url('images/precipitate.jpg');background-size: cover;">
table
{
align="center";
color: #333;
font-family: Helvetica, Arial, sans-serif;
width: 640px; 
text-align:center;
padding: 15px;
border-collapse: 
collapse; border-spacing: 0; 
}
td, th { 
border: 1px solid transparent; 
/* No more visible border */
height: 30px; 
transition: all 0.3s;  /* Simple transition for hover effect */
}
th {
background-color: #4CAF50;
    color: white; /* Darken header a bit */
font-weight: bold;
}
td {
background: #FAFAFA;
text-align: center;
}
tr:hover {background-color: #f5f5f5}
/* Cells in even rows (2,4,6...) are one color */ 
tr:nth-child(even) td { background: #F1F1F1; }   

/* Cells in odd rows (1,3,5...) are another (excludes header cells)  */ 
tr:nth-child(odd) td { background: #FEFEFE; }  

tr td:hover { background: #666; color: #FFF; } /* Hover cell effect! */
body
{
 text-align: center;
}
.container
{
 margin-left: auto;
 margin-right: auto;
 width: 40em;
}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
 $("#tablediv").hide();
     $("#showTable").click(function(event){
           $.get('PopulateTable',function(responseJson) {
            if(responseJson!=null){
                $("#countrytable").find("tr:gt(0)").remove();
                var table1 = $("#countrytable");
                $.each(responseJson, function(key,value) {
	var type=value['len'];
	var cost = type*0.6;
	var t = value['threshold'];
	console.log(type);
	if (t == 1)
		{
			var status = 'Live';
		}
	else if (t == 0)
		{
			var status = 'Deleted';
		}
	else
		var status = 'Live';
                     var rowNew = $("<tr><td></td><td></td><td></td><td></td></tr>");
                        rowNew.children().eq(0).text(value['sid']); 
                        rowNew.children().eq(1).text(value['sgp']); 
                        rowNew.children().eq(2).text(status);
						rowNew.children().eq(3).text(cost);
                        rowNew.appendTo(table1);
                });
                }
            });
            $("#tablediv").show();          
  });      
});
</script>
<script>
     var time = new Date().getTime();
     $(document.body).bind("mousemove keypress", function(e) {
         time = new Date().getTime();
     });

     function refresh() {
         if(new Date().getTime() - time >= 60000) 
             window.location.reload(true);
         else 
             setTimeout(refresh, 10000);
//         alert('inside the refresh function');
     }
     setTimeout(refresh, 10000);
</script>
</head>

<body class ="container" style="background-image:url('images/background.png');background-size: cover;">
<section>
    <div id="container_demo" >
        <div id="wrapper">
            <div id="login" class="animate form">

<h1 style="color:White;">Sensor Status</h1>
<input type="button" value="Show Sensor Status" id="showTable"/>
<div id="tablediv">
<table id="countrytable"> 
	<tr>
		<th scope="col">SensorID</th> 
		<th scope="col">SensorGP</th> 
		<th scope="col">Status</th> 
		<th scope="col">You Owe us</th>
	</tr> 
</table>
</div>
 </div>                                              
        </div>
    </div>
</section> 
</body>
</html>
