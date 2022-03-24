<?php
	require("conn.php");
	
	$query = "SELECT id, name, lat, lng FROM blood_banks";
	$result = mysqli_query($dbc,$query);
	
	while($row = mysqli_fetch_assoc($result)){
		echo $row['id']."=>".$row['name']."=>".$row['lat']."=>".$row['lng']."<br>";
	}
?>