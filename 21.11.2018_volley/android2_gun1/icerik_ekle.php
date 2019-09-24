<?php
require_once("class.database_sqlite.php");


if($_SERVER['REQUEST_METHOD']=="POST"){


$icerik = $_POST['icerik'];
$mail = $_POST['mail'];
$tarih = 	 date("d.m.Y H:i:s");


	//header('Content-Type: application/json');
	global $db2;
	try{
		// eğer mail adresi kayıtlıysa bunu, değilse bunu yap
		executeSqlite("insert into Paylasimlar values(NULL,'".$tarih."','".$mail."','".$icerik."')");
		echo "ok";
	}catch(PDOException $e){
		echo "fail";
	}
	

}

?>