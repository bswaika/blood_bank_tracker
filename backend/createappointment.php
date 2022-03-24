<?php
	require("conn.php");
	$uid = $_POST['userid'];
	$date = $_POST['date'];
	$time = $_POST['time'];
	$bname = $_POST['bname'];
	$type = $_POST['type'];
	$bid=0;
	$flag = 1;
	
	$query = "SELECT id FROM blood_banks WHERE name LIKE '$bname'";
	$result = mysqli_query($dbc,$query);
	
	if(mysqli_num_rows($result)==1){
		$bank = mysqli_fetch_assoc($result);
		$bid = $bank['id'];
	}else{
		$flag = 0;
	}
	
	$query = "INSERT INTO appointments (uid, bid, date, time, type) VALUES ('$uid','$bid','$date','$time','$type')";
	$result = mysqli_query($dbc,$query);
	
	if($result){
		$flag = 1;
	}else{
		$flag = 0;
	}
	
	if($flag==1){
		echo "1";
	}else{
		echo "0";
	}
?>