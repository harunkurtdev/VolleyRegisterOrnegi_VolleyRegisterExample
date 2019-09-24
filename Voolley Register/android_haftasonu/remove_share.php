<?php
include("class.database_sqlite.php");

$shareid = @$_POST["shareid"];
$userid = @$_POST["userid"];

global $db2;
$userid = $db2->query("delete from User_Share where user_id=".$userid." and id=".$shareid)->fetchAll();

echo "ok";

?>