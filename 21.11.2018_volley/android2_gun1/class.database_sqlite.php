<?php
//error_reporting(E_ALL);
//ini_set("display_errors", 1);
// Set default timezone
date_default_timezone_set('UTC');

try {
    // Create (connect to) SQLite database in file
    $db2 = new PDO('sqlite:offline_db.db');
    // Set errormode to exceptions
    $db2->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);
}catch(PDOException $e) { // Print PDOException message
	echo $e->getMessage();
}

function getPaylasimlar(){
	global $db2;
	$paylasimlar = $db2->query("select * from Paylasimlar ORDER BY id DESC")->fetchAll(PDO::FETCH_ASSOC);
    return $paylasimlar;
}

function selectCountInTable_OptionalValues_sqlite($TABLE,$WHERE){
	global $db2;
	$nRows = $db2->query('SELECT COUNT(*) from '.$TABLE.' WHERE '.$WHERE)->fetchColumn();
	return $nRows;	
}

function executeSqlite($str){
	global $db2;
	$insert = $str;
	$stmt = $db2->exec($insert);
}

function closeDB2(){
	//global $db2;
	//$db2->close();
}
  
  
?>