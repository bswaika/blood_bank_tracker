<?php
	require("conn.php");
	$id = $_POST['id'];
	$query = "SELECT * FROM users WHERE id = '$id'";
	$result = mysqli_query($dbc,$query);
	
	if($result){
		$user = mysqli_fetch_assoc($result);
		$name = $user['name'];
		$contact = $user['contact'];
		$email = $user['email'];
		$bgroup = $user['bgroup'];
		
		echo $name."<br>".$bgroup."<br>".$contact."<br>".$email;
		
	}
?>