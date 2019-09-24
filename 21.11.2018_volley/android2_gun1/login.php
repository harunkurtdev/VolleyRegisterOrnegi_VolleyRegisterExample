<?php
require_once("class.database_sqlite.php");


if($_SERVER['REQUEST_METHOD']=="POST"){

$email = $_POST['email'];
$password = $_POST['password'];


	//header('Content-Type: application/json');
	global $db2;
	try{
		// eğer mail adresi kayıtlıysa bunu, değilse bunu yap
		if(selectCountInTable_OptionalValues_sqlite("User","email='".$email."' and password='".$password."'")==1){
			echo "100";
		}else{
			echo "101";
		}
		/*
			100 - kullanıcı adı ve şifre doğru
			101 - kullanıcı adı veya şifre hatalı
			102 - pdo exception
		
		*/
		
	}catch(PDOException $e){
		echo "102";
	}
	

}

?>