<?php

include_once "../DocumentSubmission.php";
$docObject = new DocumentSubmission();

switch ($_SERVER['REQUEST_METHOD']) {
    case 'POST':
        header("Content-Type:application/json");
        switch ($_POST['category']) {
            case 'register':
                echo json_encode($docObject->submitDocument($_POST));
                break;

            case 'update':
                echo json_encode($docObject->updatedocument($_POST));
                break;

        }

        break;
    case 'GET':
        header("Content-Type:application/json");
        switch ($_GET['category']) {

            case 'delete':
                echo json_encode($docObject->deleteDocument($_GET['id']));
                break;

            case 'getById':
                // header("Content-Type:application/json");
                echo json_encode($docObject->getdocument($_GET['id']));
                break;

            case 'bystatus':
                // header("Content-Type:application/json");
                echo json_encode($docObject->getByStatus($_GET));
                break;


            case 'get':
                // header("Content-Type:application/json");
                echo json_encode($docObject->All_documents());
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