<?php
include("class.database_sqlite.php");

$userid = @$_POST["userid"];

global $db2;
$shares = $db2->query("select * from User_Share where user_id=".$userid." order by id desc")->fetchAll();


//print_r($shares);

echo '
	{
	    "UserShares": [';
for($i=0; $i<count($shares); $i++){
	echo '{
	            "id": "'.$shares[$i]['id'].'",
	            "user_id": "'.$shares[$i]['user_id'].'",
	            "share_time": "'.$shares[$i]['share_time'].'",
	            "share_content": "'.$shares[$i]['share_content'].'"
	        }';
	if($i!=count($shares)-1){
		echo ',';
	} 	
}
echo '	    ]
	}
	';	



header("Content-type: application/json; charset=utf-8");



?>