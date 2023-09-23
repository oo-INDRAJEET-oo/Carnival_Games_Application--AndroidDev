<?php
$host = 'ticketdb.cvh7mcn3tncr.ap-south-1.rds.amazonaws.com';
$user = 'admin';
$pass = 'jithu007';
$db_name = 'DemoDB';

$conn =null;
$conn = new mysqli($host, $user, $pass, $db_name);
if($conn==null){
echo "nop";}

else {
$q =$_GET['q'];

// $dayimage = $day."Image";
// $dayscan=$day."scan";
// $ticket=$_POST['param2'];
// $datetime =$_POST['param3'];
$sql = "select * from fsdb where mail= '".$q."'";

// Execute query
$result = $conn->query($sql);// mysqli_query($conn, $sql);
if ($result->num_rows > 0) {
	$row = $result->fetch_all(MYSQLI_ASSOC);
	$myJSON = json_encode($row);
	echo $myJSON;
}

else{
	echo "0";
}

/*if ($result->num_rows > 0) {
	// Output the data in an HTML table
	echo "<table>";
	echo "<tr><th>ID</th><th>Name</th><th>Email</th></tr>";
	while($row = $result->fetch_assoc()) {
	  echo "<tr>";
	  echo "<td>".$row["name"]."</td>";
	  echo "<td>".$row["amount"]."</td>";
	  echo "<td>".$row["points"]."</td>";
	  echo "</tr>";
	}
	echo "</table>";
  } else {
	echo "No results found";
  }
*/




/*if (mysqli_num_rows($result) ==0){
echo "failure";}
else echo "success";
	*/
/*
// Process result
if (mysqli_num_rows($result) ==1) {
    // Output data of each row
	$row = mysqli_fetch_assoc($result1);
	if($row[$dayscan]== 0) {
		$sql = "UPDATE usertickets SET ".$dayscan." = 1,checkedDateTime = '".$datetime."' WHERE ".$dayimage."= '".$ticket."'";
		if ($conn->query($sql) === FALSE) {
			echo json_encode(array(
				"mail" => "error1",
				));
		}
		else{
			echo json_encode(mysqli_fetch_assoc($result));}
	}
	else {
		echo json_encode(array(
    "mail" => "error2",
	"dATETIME" => $row["checkedDateTime"],
	"Name" =>  $row["name"],
	"RollNo" =>  $row["regno"],
	"CollegeName" =>  $row["college"],
	"premium" => $row[$daypremium],
		));
	}
    
}
else echo json_encode(array(
    "mail" => "error3",
));*/

mysqli_close($conn);}
?>