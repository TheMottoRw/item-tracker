<?php
class documentType{ 
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

 
 function insertDocumentType($arr){

    
    $document_name = $arr['document_name'];
    $regdate = $arr['regi_date'];
   

   	$insert = $this->conn->prepare("INSERT INTO document_type set document_name=:docname,regi_date=:dat");

  	$insert->execute(array('docname'=>$document_name,'dat'=>$regdate));
  	if($insert->rowCount()>0){
  		echo "you added document type ".$this->conn->lastInsertId();
      
  	}
  	else{
  		echo "failed to add".json_encode($insert->errorInfo());
  	}
  }

  // update info 
   function updatedocumentType($arr){

    $document_name = $arr['document_name'];
    $regdate = $arr['regi_date'];
    $id = $arr['doc_id'];
    
  
  $upd=$this->conn->prepare("UPDATE document_type set document_name=:docname,regi_date=:dat where doc_id=:i");

  $upd->execute(array('docname'=>$document_name,'dat'=>$regdate,'i'=>$id));

  if($upd->rowCount()>0){
  	echo "database updated";

  }
 else {
 	echo "failed to update".json_encode($upd->errorInfo());
 } 
   }

   // delete documentType
   function deletedocumentType($id){
   	$del = $this->conn->prepare("DELETE from document_type S where id=:i");
   	$del->execute(array('i' =>$id));
   	if ($del){
   		echo "deleted succeffully";
   	}
    else{
     echo "failed to delete".json_encode($del->errorInfo());
   }
    }
    
  
    function All_documentsType(){
      $getall = $this->conn->prepare("SELECT * from documents");
      $getall->execute();
      $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;   
    }

   

}

?>