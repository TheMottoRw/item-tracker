<?php
include_once "Database.php";

class submitDocuments
{
    private $conn;

    function __construct()
    {
        $db = new Database();
        $this->conn = $db->getInstance();
    }


 // declare document
 function submitDocument($arr){
  $response = ['status' => 'ok', 'message' => "Submitted successful", 'id' => 0];

    $doc_type = $arr['document_type'];
    $name = $arr['name'];
    $document_id = $arr['document_id'];
    $document_picture = $arr['document_picture'];
    $status = $arr['status'];
    $added_by = $arr['added_by'];
    $given_by = $arr['given_by'];
  
   
   	$insert = $this->conn->prepare("INSERT INTO submitted_documents set document_type=:doc,document_id=:docid,document_picture=:dpic,name=:name,status=:stat,added_by=:add,given_by=:given");

  	$insert->execute(array('doc'=>$doc_type,'docid'=>$document_id,'name'=>$name,'dpic'=>$document_picture,'stat'=>"Available",'add'=>$added_by,'given'=>$given_by));
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
    $status = $arr['status'];
    $added_by = $arr['added_by'];
    $given_by = $arr['given_by'];
    $id = $arr['id'];
    
  
  $upd=$this->conn->prepare("UPDATE submitted_documents set document_type=:doc,document_id=:docid,document_picture=:dpic,name=:name,status=:stat,added_by=:add,given_by=:given where id=:i");

  $upd->execute(array('doc'=>$doc_type,'docid'=>$document_id,'name'=>$name,'dpic'=>$document_picture,'stat'=>"Available",'add'=>$added_by,'given'=>$given_by,'i'=>$id));

  if ($upd->rowCount() == 0) {
            $response = ['status' => 'fail', 'message' => "failed to update submitted document", 'id' => $id, 'error' => $upd->errorInfo()];
        }
        return $response;
  }

   // delete document
   function deletedocument($id){

    $response = ['status' => 'ok', 'message' => "Successful deleted document type", 'id' => 0];

   	$del = $this->conn->prepare("UPDATE submitted_documents SET status=:stat where id=:i ");
   	$del->execute(array('stat'=>"deleted",'i' =>$id));
   	if ($del->rowCount() == 0) {
            $response = ['status' => 'fail', 'message' => "Failed to delete", 'id' => $id, "error" => $del->errorInfo()];
        }
    }
    
    //  retrieve 
    function getdocument($id){
    	$getall = $this->conn->prepare("SELECT * from submitted_documents where id=:i");
    	$getall->execute(array('i'=>$id));
    	$data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;   
    }

     function active_document(){
      $getall = $this->conn->prepare("SELECT * from submitted_documents where status='available'");
      $getall->execute();
      $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;   
    }

     function deleted_document(){
      $getall = $this->conn->prepare("SELECT * from submitted_documents where status='deleted'");
      $getall->execute();
      $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;   
    }

    function All_documents(){
      $getall = $this->conn->prepare("SELECT * from submitted_documents");
      $getall->execute();
      $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;   
    }

   

}

?>