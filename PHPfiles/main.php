
<html>
<title>Score Board</title>

<head>

<link rel="stylesheet" href="style.css">
<script src="script.js"></script>
</head>

<body>
  <div class="all">
  <div id="title"><h1 id="gg">Score Board</h1></div>

  <div id="result">
    <table id="tab">
      <tr>
        <th>Name</th>

        <th>College</th>
        <th>Todays Points</th>
        
      </tr>
      <tr>
        <td id="name"></td>
        <td id="rno"></td>
        <td id="college"></td>
        <td id="Totalpoints"></td>
      </tr>

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
      
      $sql = "select * from fsdb order by day3points desc limit 20";
      $result = mysqli_query($conn,$sql);
      
      if(mysqli_num_rows($result)>1){
        
                  
          
          while($row=mysqli_fetch_assoc($result)){
              echo "<tr><td>".$row['name']."</td><td>".$row['college']."</td><td>".$row['day3points']."<tr>";
          }        
      } 
      }
?>
    </table>
    </div>

  </div>
</body>

</html>
