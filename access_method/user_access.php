<?php

include_once "../Residents.php";
$userObj = new Residents();

switch ($_SERVER['REQUEST_METHOD']) {
    case 'POST':
        header("Content-Type:application/json");
        switch ($_POST['category']) {
            case 'register':
                echo json_encode($userObj->insertUser($_POST));
                break;

            case 'update':
                echo json_encode($userObj->updateUser($_POST));
                break;

        }

        break;
    case 'GET':
        header("Content-Type:application/json");
        switch ($_GET['category']) {

            case 'login':
                // header("Content-Type:application/json");
                echo json_encode($userObj->login($_GET['name'], $_GET['password']));
                break;

            case 'getById':
                // header("Content-Type:application/json");
                echo json_encode($userObj->getUser($_GET['id']));
                break;

            case 'get':
                // header("Content-Type:application/json");
                echo json_encode($userObj->getUsers());
                break;
            case 'delete':
                // header("Content-Type:application/json");
                echo json_encode($userObj->delete($_GET['id']));
                break;


            default:
                echo json_encode(['status' => 'nocategory', 'message' => "value of parameter category not known"]);
                break;
        }
        break;
    default:
        echo json_encode(['status' => 'methodnotallowed', 'message' => $_SERVER['REQUEST_METHOD'] . "Request method not allowed"]);
        break;
}

?>