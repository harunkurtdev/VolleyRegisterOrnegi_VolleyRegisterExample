<?php
require_once("class.database_sqlite.php");

global $db2;
$json = array();
$array = getPaylasimlar();


for($i=0; $i<count($array); $i++){
	$itemObject = new stdClass();
	$itemObject->id = $array[$i]['id'];
	$itemObject->tarih = $array[$i]['tarih'];
	$itemObject->paylasan_mail = $array[$i]['paylasan_mail'];
	$itemObject->paylasim_icerik = $array[$i]['paylasim_icerik'];
	array_push($json, $itemObject);
}


	$response = array('paylasimlar' => $json);
	echo json_encode($response, JSON_PRETTY_PRINT);


//getFruit($db2);
header('Content-Type: application/json');

?>