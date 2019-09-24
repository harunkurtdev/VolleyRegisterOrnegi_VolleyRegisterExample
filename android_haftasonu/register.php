<?php
include("class.database_sqlite.php");

$email = $_POST["email"];
$password = $_POST["pass"];
$adsoyad = $_POST["adsoyad"];
$sehir = $_POST["sehir"];
$dogumtarihi = $_POST["dogumtarihi"];
$tarih = date('d.m.Y H:i:s');



executeSqlite("insert into Users values(NULL,'".$adsoyad."','".$email."','".$password."','".$sehir."','".$dogumtarihi."','".$tarih."')");
echo "ok";


?>