<?php

include_once "../appusers.php";
$userObj = new users();

switch ($_SERVER['REQUEST_METHOD']) {
  case 'POST':
    switch ($_POST['category']) {
          case 'insertUser':
      echo json_encode($userObj->insertUser($_POST));
      break;

        case 'updateUser':
   echo json_encode( $userObj->updateUser($_POST));
    break;

    }

		break;
	case 'GET':
	switch ($_GET['category']) {

         case 'login':
        // header("Content-Type:application/json");
        echo json_encode($userObj->login($_GET['name'],$_GET['password']));
              break;
    
        case 'getUser':
        // header("Content-Type:application/json");
        echo json_encode($userObj->getUser($_GET['id']));
              break;

        case 'getUsers':
        // header("Content-Type:application/json");
        echo json_encode($userObj->getUsers());
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