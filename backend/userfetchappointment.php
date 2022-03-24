<?php
	require("conn.php");
	$id = $_POST['id'];
	$query = "SELECT * FROM appointments,blood_banks WHERE uid = '$id' AND blood_banks.id = appointments.bid;";
	$result = mysqli_query($dbc,$query);
	
	
		while($appoint = mysqli_fetch_assoc($result)){
			
			$q = "SELECT * FROM blood_banks WHERE bid = ".$appoint['bid'].";";
			
			
			//echo mysqli_query($dbc,$q);
			
			//$bank = mysqli_fetch_assoc($r);
			$name = $appoint['name'];
			$address = $appoint['address'].",".$appoint['city'].",".$appoint['district'].",".$appoint['state'].",".$appoint['pincode'];
			$contact = $appoint['contact'];
				
			echo $name."=>".$address."=>".$contact."=>".$appoint['date']."=>".$appoint['time']."=>".$appoint['type']."<br>";
		}
		

?>