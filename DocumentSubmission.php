<?php
include_once "Database.php";
include_once "declareDocument.php";

class DocumentSubmission
{
    private $conn;

    function __construct()
    {
        $db = new Database();
        $this->conn = $db->getInstance();
        $this->declaredDocs = new declaredDocument();
    }


 // declare document
 function submitDocument($arr){
  $response = ['status' => 'ok', 'message' => "Submitted successful", 'id' => 0];

     $doc_type = $arr['document_type'];
     $name = $arr['name'];
     $document_id = $arr['document_id'];
     $document_picture = $arr['document_picture'];
     $addedBy = $arr['added_by'];

     //check if doc has been declared
     $docInfo = $this->declaredDocs->getBySerial($document_id);
     if(count($docInfo) > 0){
         $qy = $this->conn->prepare("UPDATE declared_documents SET status=:status WHERE document_id=:docid");
         $qy->execute(['status'=>'found','docid'=>$document_id]);
     }

   	$insert = $this->conn->prepare("INSERT INTO submitted_documents set document_type=:doc,document_id=:docid,document_picture=:dpic,name=:name,added_by=:addby");

  	$insert->execute(array('doc'=>$doc_type,'docid'=>$document_id,'name'=>$name,'dpic'=>$document_picture,'addby'=>$addedBy));
  	if($insert->rowCount()>0){
  		$response['message'] = "you submitted a document ";
            $response['id'] = $this->conn->lastInsertId();
        } else {
            $response = ['status' => 'fail', 'message' => "failed to submit a document", 'error' => $insert->errorInfo()];
        }
        return $response;
    }

  // update info 
   function updatedocument($arr){

    $response = ['status' => 'ok', 'message' => "Successful updated ", 'id' => 0];

    $doc_type = $arr['document_type'];
    $name = $arr['name'];
    $document_id = $arr['document_id'];
    $document_picture = $arr['document_picture'];
    $id = $arr['id'];

       //check if doc has been declared
       $docInfo = $this->declaredDocs->getBySerial($document_id);
       if(count($docInfo) > 0){
           $qy = $this->conn->prepare("UPDATE declared_documents SET status=:status WHERE document_id=:docid");
           $qy->execute(['status'=>'found','docid'=>$document_id]);
       }

       $upd=$this->conn->prepare("UPDATE submitted_documents set document_type=:doc,document_id=:docid,document_picture=:dpic,name=:name where id=:i");

  $upd->execute(array('doc'=>$doc_type,'docid'=>$document_id,'name'=>$name,'dpic'=>$document_picture,'stat'=>"Available",'add'=>$added_by,'given'=>$given_by,'i'=>$id));

  if ($upd->rowCount() == 0) {
            $response = ['status' => 'fail', 'message' => "failed to update submitted document", 'id' => $id, 'error' => $upd->errorInfo()];
        }
        return $response;
  }

   // delete document
   function deleteDocument($id){

    $response = ['status' => 'ok', 'message' => "Successful deleted document type", 'id' => 0];

   	$del = $this->conn->prepare("UPDATE submitted_documents SET status=:stat where id=:i ");
   	$del->execute(array('stat'=>"deleted",'i' =>$id));
   	if ($del->rowCount() == 0) {
            $response = ['status' => 'fail', 'message' => "Failed to delete", 'id' => $id, "error" => $del->errorInfo()];
        }
   	return $response;
    }
    
    //  retrieve 
    function getdocument($id){
    	$getall = $this->conn->prepare("SELECT sd.*,dt.document_name from submitted_documents sd  INNER JOIN document_types dt ON sd.document_type=dt.doc_id  where sd.id=:i");
    	$getall->execute(array('i'=>$id));
    	$data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;   
    }

     function getByStatus($arr){
      $getall = $this->conn->prepare("SELECT sd.*,dt.document_name from submitted_documents sd  INNER JOIN document_types dt ON sd.document_type=dt.doc_id where sd.status=:status");
      $getall->execute(['status'=>$arr['status']]);
      $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;   
    }

    function All_documents(){
      $getall = $this->conn->prepare("SELECT sd.*,dt.document_name from submitted_documents sd  INNER JOIN document_types dt ON sd.document_type=dt.doc_id");
      $getall->execute();
      $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;   
    }

   

}

?>