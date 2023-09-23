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

$day =$_POST['param1'];
$dayimage = $day."Image";
$ticket=$_POST['param2'];
$choose = $_POST['param3'];
$sql = "select * from usertickets where  ".$dayimage."= '".$ticket."'";

// Execute query
$result =  mysqli_query($conn, $sql);
$result1 = mysqli_query($conn, $sql);

// Process result
if (mysqli_num_rows($result) ==1) {
    // Output data of each row
	$row = mysqli_fetch_assoc($result1);
	$sql = "select * from fsdb where mail = '".$row["mail"]."'";
	$result1 = mysqli_query($conn,$sql);
	$row1=mysqli_fetch_assoc($result1);
	if(mysqli_num_rows($result1)==0 && $choose!="redeem") {
		$sql = "INSERT INTO fsdb VALUES ('".$row["mail"]."',
				'".$row["name"]."','".$row["regno"]."','".$row["college"]."',0,0,0,0,0,0)";
		$result =mysqli_query($conn,$sql);
		
		echo json_encode(array(
	"Mail" => $row["mail"],
	"Name" =>  $row["name"],
	"RollNo" =>  $row["regno"],
	"CollegeName" =>  $row["college"],
	"Amount" => 0,
	"Points" => 0
		));;
	}
	else if(mysqli_num_rows($result1)==0 && $choose == "redeem"){
		echo json_encode(array(
	"Name" => "errorredeem"
		));
	}
	else{
		echo json_encode(array(
	"Mail" => $row1["mail"],
	"Name" =>  $row1["name"],
	"RollNo" =>  $row1["rollno"],
	"CollegeName" =>  $row1["college"],
	"Amount" => $row1["amount"],
	"Points" => $row1["points"],
	"RedeemPoints" => $row1["redeempoints"]
		));
	}
    
}
else echo json_encode(array(
    "Name" => "error1",
));

mysqli_close($conn);}
?>