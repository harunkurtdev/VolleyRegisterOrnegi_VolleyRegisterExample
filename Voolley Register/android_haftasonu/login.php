<?php
include("class.database_sqlite.php");

$email = $_POST["email"];
$password = $_POST["pass"];

global $db2;
$userid = $db2->query("select * from Users where email='".$email."' and sifre='".$password."'")->fetchColumn();


if($userid==-1 || $userid==""){
	echo "fail";
}else{
	echo "ok";
}


?>