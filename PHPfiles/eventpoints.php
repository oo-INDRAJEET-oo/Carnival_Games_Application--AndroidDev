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
else
{
$mail= $_POST['param1'];
$point =$_POST['param2'];
$day =$_POST['param3'];
$sql = "select * from fsdb where mail ='".$mail."'";

$result = mysqli_query($conn,$sql);
if(mysqli_num_rows($result)==1){
	$row=mysqli_fetch_assoc($result);
	
	$pointss = $row['points'] + (int)$point;
	$redeempoints = $row['redeempoints'] + (int)$point;
    $events = $row['events']+(int)1;
    $day2points = $row['day2points'];
$day3points = $row['day3points'];
    if($day=="day2")
    {
       $day2points = $row['day2points']+(int)$point;
    }
    else if($day=="day3")
    {
      $day3points = $row['day3points']+(int)$point;

    }
	$sql = "UPDATE fsdb SET points = ".$pointss." , redeempoints = ".$redeempoints." ,day2points = ".$day2points.",day3points = ".$day3points.",events = ".$events." WHERE mail = '".$mail."'";
	
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