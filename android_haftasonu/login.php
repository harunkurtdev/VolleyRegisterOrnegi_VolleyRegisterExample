<?php
include("class.database_sqlite.php");

$email = @$_POST["email"];
$password = @$_POST["pass"];

global $db2;
$userid = $db2->query("select * from Users where email='".$email."' and sifre='".$password."'")->fetchAll();

if($userid==null || $userid==""){
	echo "fail";
}else{
	echo '
	{
	    "userdetail": [
	        {
	            "id": "'.$userid[0]['id'].'",
	            "adsoyad": "'.$userid[0]['adsoyad'].'",
	            "email": "'.$userid[0]['email'].'",
	            "sehir": "'.$userid[0]['sehir'].'",
	            "dogumtarihi": "'.$userid[0]['dogumtarihi'].'",
	            "kayit_tarihi": "'.$userid[0]['kayit_tarihi'].'"
	        }
	    ]
	}
	';
}

header("Content-type: application/json; charset=utf-8");

?>