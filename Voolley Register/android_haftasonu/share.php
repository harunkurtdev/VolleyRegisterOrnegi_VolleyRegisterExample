<?php
include("class.database_sqlite.php");

$userid = $_POST["userid"];
$paylasim_yazisi = $_POST["paylasim_yazisi"];
$tarih = date('d.m.Y H:i:s');



executeSqlite("insert into User_Share values(NULL,'".$userid."','".$tarih."','".$paylasim_yazisi."')");
echo "ok";


?>