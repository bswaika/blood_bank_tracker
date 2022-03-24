<?php
	require("conn.php");
	$name = $_POST['name'];
	$age = $_POST['age'];
	$contact = $_POST['contact'];
	$email = $_POST['email'];
	$password = $_POST['password'];
	$address = $_POST['address'];
	$bgroup = $_POST['bgroup'];
	$sex = $_POST['gender'];
	$query = "INSERT INTO users (name, age, contact, email, password, address, bgroup, sex) VALUES ('$name','$age','$contact','$email','$password','$address','$bgroup','$sex')";
	$result = mysqli_query($dbc,$query);
	
	if($result){
		echo mysqli_insert_id($dbc);
		
	}else{
		echo "0";
	}
?>