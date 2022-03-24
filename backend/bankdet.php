<?php
	require("conn.php");
	$id = $_POST['id'];
	$query = "SELECT * FROM blood_banks WHERE name LIKE '$id'";
	$result = mysqli_query($dbc,$query);
	
	if($result){
		$bank = mysqli_fetch_assoc($result);
		$name = $bank['name'];
		$address = $bank['address'].",".$bank['city'].",".$bank['district'].",".$bank['state'].",".$bank['pincode'];
		$contact = $bank['contact'];
		$email = $bank['email'];
		
		echo $name."<br>".$address."<br>".$contact."<br>".$email;
		
	}
?>