<?php

include_once "../Admin.php";
$adminObj = new Admin();

switch ($_SERVER['REQUEST_METHOD']) {
    case 'POST':
        switch ($_POST['cate']) {
            case 'register':
                echo json_encode($adminObj->insertAdmin($_POST));
                break;

            case 'update':
                echo json_encode($adminObj->updateAdmin($_POST));
                break;

        }

        break;
    case 'GET':
         header("Content-Type:application/json");
        switch ($_GET['category']) {
            case 'delete':
                echo json_encode($adminObj->deleteAdmin($_GET['id']));
                break;

            case 'getBySectors':
                // header("Content-Type:application/json");
                echo json_encode($adminObj->getAdminSectors($_GET['category'], $_GET['sector']));
                break;

            case 'get':
                // header("Content-Type:application/json");
                echo json_encode($adminObj->All_Admin());
                break;

            case 'byId':
                // header("Content-Type:application/json");
                echo json_encode($adminObj->getById($_GET));
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