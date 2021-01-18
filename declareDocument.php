<?php
class documents{ 
	private $host='localhost';
	private $user='root';
	private $password='';
	private $db='declareddocuments';
	private $conn;

 function __construct(){
 	try{
 	 $this->conn= new PDO("mysql:host=".$this->host.";dbname=".$this->db, $this->user,$this->password);	
 	}catch (PDOException $exc){
 		echo "failed to connect".$exc->getMessage();
 	}
 }

 // declare document
 function declareDocument($arr){

    $doc_type = $arr['docType'];
   $document_id = $arr['document_id'];
    $name = $arr['name'];
    $phone = $arr['phone'];
    $gender = $arr['gender'];
    $status = $arr['status'];

   
   	$insert = $this->conn->prepare("INSERT INTO documents set docType=:doc,document_id=:docid,name=:n,phone=:phone,gender=:gender,status=:status");

  	$insert->execute(array('doc'=>$doc_type,'docid'=>$document_id,'n'=>$name,'phone'=>$phone,'gender'=>$gender,'status'=>$status));
  	if($insert->rowCount()>0){
  		echo "you added activity ".$this->conn->lastInsertId();
      
  	}
  	else{
  		echo "failed to add".json_encode($insert->errorInfo());
  	}
  }

  // update info 
   function updatedocument($arr){

    $doc_type = $arr['docType'];
   $document_id = $arr['document_id'];
    $name = $arr['name'];
    $phone = $arr['phone'];
    $gender = $arr['gender'];
    $status = $arr['status'];
    $id = $arr['id'];
    
  
  $upd=$this->conn->prepare("UPDATE documents set docType=:doc,document_id=:docid,name=:n,phone=:phone,gender=:gender,status=:status where id=:i");

  $upd->execute(array('doc'=>$doc_type,'docid'=>$document_id,'n'=>$name,'phone'=>$phone,'gender'=>$gender,'status'=>$status,'i'=>$id));

  if($upd->rowCount()>0){
  	echo "database updated";

  }
 else {
 	echo "failed to update".json_encode($upd->errorInfo());
 } 
   }

   // delete document
   function deletedocument($phone){
   	$del = $this->conn->prepare("UPDATE documents SET status=:stat where phone=:p ");
   	$del->execute(array('stat'=>"deleted",'p' =>$phone));
   	if ($del){
   		echo "deleted succeffully";
   	}
    else{
     echo "failed to delete".json_encode($del->errorInfo());
   }
    }
    
    //  retrieve 
    function getdocument($phone){
    	$getall = $this->conn->prepare("SELECT * from documents where phone=:p");
    	$getall->execute(array('p'=>$phone));
    	$data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;   
    }

     function active_document(){
      $getall = $this->conn->prepare("SELECT * from documents where status='available'");
      $getall->execute();
      $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;   
    }

     function deleted_document(){
      $getall = $this->conn->prepare("SELECT * from documents where status='deleted'");
      $getall->execute();
      $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;   
    }

    function All_documents(){
      $getall = $this->conn->prepare("SELECT * from documents");
      $getall->execute();
      $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;   
    }

   

}

?>