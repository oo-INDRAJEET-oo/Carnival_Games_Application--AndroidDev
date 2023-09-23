<?php

$host = 'ticketdb.cvh7mcn3tncr.ap-south-1.rds.amazonaws.com';
$user = 'admin';
$pass = 'jithu007';
$db_name = 'DemoDB';

$conn =null;
$conn = new mysqli($host, $user, $pass, $db_name);
if($conn==null){
echo json_encode(array(
				"status" => "error1",
				));}

else {
$mail=$_POST['param1'];
$ramount =$_POST['param2'];
$sql = "select * from fsdb where mail ='".$mail."'";
$result = mysqli_query($conn,$sql);
if(mysqli_num_rows($result)==1){
	$row=mysqli_fetch_assoc($result);
	$ramount = $row['amount'] + (int)$ramount;
	$sql = "UPDATE fsdb SET amount = ".$ramount." WHERE mail = '".$mail."'";
	if ($conn->query($sql) === FALSE) {
		echo json_encode(array(
				"status" => "error1",
				));
	}
	else{
		echo json_encode(array(
				"status" => "success",
				));
	}
	
}

else{
	echo json_encode(array(
				"status" => "error2",
				));
}
	

}