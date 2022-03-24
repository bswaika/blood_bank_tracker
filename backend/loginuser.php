<?php
	require("conn.php");
	$email = $_POST['email'];
	$password = $_POST['password'];
	$query = "SELECT id FROM users WHERE email = '$email' AND password='$password'";
	$result = mysqli_query($dbc,$query);
	
	if(mysqli_num_rows($result)==1){
		$row = mysqli_fetch_assoc($result);
		echo $row['id'];
		
	}else{
		echo "0";
	}
?>