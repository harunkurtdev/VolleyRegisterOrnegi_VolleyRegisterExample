<?php
require_once("class.database_sqlite.php");


if($_SERVER['REQUEST_METHOD']=="POST"){


$city = $_POST['city'];
$country= $_POST['country'];
$email = $_POST['email'];
$nameSurname = $_POST['nameSurname'];
$password = $_POST['password'];
$kayit_tarih = 	 date("d.m.Y H:i:s");


	//header('Content-Type: application/json');
	global $db2;
	try{
		// eğer mail adresi kayıtlıysa bunu, değilse bunu yap
		if(selectCountInTable_OptionalValues_sqlite("User","email='".$email."'")==0){
		    /*
			executeSqlite("INSERT INTO Rehber VALUES(NULL,'".$rehber_deviceid."','".$rehber_json."','".$rehber_tarih."')");
			*/
			
			
			executeSqlite("insert into User values(NULL,'".$nameSurname."','".$email."','".$country."','".$city."','".$password."','".$kayit_tarih."')");
			
			
		}
		echo "ok";
	}catch(PDOException $e){
		echo "fail";
	}
	

}

?>