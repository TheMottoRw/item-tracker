<?php

include_once "../submitted_document.php";
$docObject = new submitDocuments();

switch ($_SERVER['REQUEST_METHOD']) {
	case 'POST':
		switch ($_POST['category']) {
          case 'submitDocument':
			echo $docObject->submitDocument($_POST);
			break;

        case 'updatedocument':
		$docObject->updatedocument($_POST);
		break;

		}

		break;
	case 'GET':
	switch ($_GET['category']) {

        case 'deletedocument':
        $docObject->deletedocument($_GET['id']); 
         break;
        
        case 'getdocument':
        // header("Content-Type:application/json");
        echo json_encode($docObject->getdocument($_GET['id']));
              break;
     
        case 'active_document':
        // header("Content-Type:application/json");
        echo json_encode($docObject->active_document());
              break;

        case 'deleted_document':
        // header("Content-Type:application/json");
        echo json_encode($docObject->deleted_document());
              break;      

        case 'All_documents':
        // header("Content-Type:application/json");
        echo json_encode($docObject->All_documents());
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