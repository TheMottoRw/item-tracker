<?php

include_once "../admin.php";
$adminObj = new admin();

switch ($_SERVER['REQUEST_METHOD']) {
	case 'POST':
		switch ($_POST['category']) {
          case 'insertAdmin':
			echo json_encode($adminObj->insertAdmin($_POST));
			break;

        case 'updateAdmin':
		echo json_encode($adminObj->updateAdmin($_POST));
		break;

		}

		break;
	case 'GET':
	switch ($_GET['category']) {

        case 'deleteAdmin':
       echo json_encode($adminObj->deleteAdmin($_GET['id'])); 
         break;
        
        case 'getAdminSectors':
        // header("Content-Type:application/json");
        echo json_encode($adminObj->getAdminSectors($_GET['category'],$_GET['sector']));
              break;

        case 'All_Admin':
        // header("Content-Type:application/json");
        echo json_encode($adminObj->All_Admin());
              break;      

		default:
			echo "value of parameter category not known";
			break;
	}
	break;
	default:
		echo $_SERVER['REQUEST_METHOD']."Request method not allowed";
		break;
}

?>