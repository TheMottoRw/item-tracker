<?php

include_once "../Helper.php";
$helperObj = new Helper();

switch ($_SERVER['REQUEST_METHOD']) {
    case 'POST':
        switch ($_POST['category']) {
            case 'login':
                header("Content-Type:application/json");
                echo json_encode($helperObj->login($_POST));
                break;
        }

        break;
    case 'GET':
        header("Content-Type:application/json");
        switch ($_GET['category']) {
            case 'deleteAdmin':
//                echo json_encode($helperObj->deleteAdmin($_GET['id']));
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