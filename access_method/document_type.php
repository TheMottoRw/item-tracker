<?php

include_once "../declareDocument.php";
$docObject = new documentType();

switch ($_SERVER['REQUEST_METHOD']) {
	case 'POST':
		switch ($_POST['category']) {
          case 'insertDocumentType':
			echo json_encode($docObject->insertDocumentType($_POST));
			break;

        case 'updatedocumentType':
	echo json_encode($docObject->updatedocumentType($_POST));
		break;

		}

		break;
	case 'GET':
	switch ($_GET['category']) {

        case 'deletedocumentType':
      echo json_encode($docObject->deletedocumentType($_GET['id'])); 
         break;
        

        case 'All_documentsType':
        // header("Content-Type:application/json");
        echo json_encode($docObject->All_documentsType());
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