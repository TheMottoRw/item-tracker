<?php

include_once "../DocumentTypes.php";
$docObject = new DocumentTypes();

switch ($_SERVER['REQUEST_METHOD']) {
    case 'POST':
        header("Content-Type:application/json");
        switch ($_POST['category']) {
            case 'register':
                echo json_encode($docObject->insertDocumentType($_POST));
                break;

            case 'update':
                echo json_encode($docObject->updatedocumentType($_POST));
                break;

        }

        break;
    case 'GET':
        header("Content-Type:application/json");
        switch ($_GET['category']) {

            case 'delete':
                echo json_encode($docObject->deletedocumentType($_GET['id']));
                break;


            case 'get':
                // header("Content-Type:application/json");
                echo json_encode($docObject->All_documentsType());
                break;
            case 'getbyid':
                // header("Content-Type:application/json");
                echo json_encode($docObject->getById($_GET));
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