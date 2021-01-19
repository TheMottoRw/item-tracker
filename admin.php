<?php
class admin{ 
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

 // declare 
 function insertAdmin($arr){

    $name = $arr['name'];
    $category = $arr['category'];
    $phone = $arr['phone'];
    $national_id= $arr['nid'];
    $sector = $arr['sector'];
    $apssword = $arr['password'];
    $done_by = $arr['done_by'];

   	$insert = $this->conn->prepare("INSERT INTO admin set name=:name,category=:cat,phone=:phone,nid=:nid,sector=:sec,password=:password,done_by=:done");

  	$insert->execute(array('name'=>$name,'cat'=>$category,'phone'=>$phone,'nid'=>$nid,'sec'=>$sector,'password'=>$password,'done'=>$done_by));
  	if($insert->rowCount()>0){
  		echo "you added admin ".$this->conn->lastInsertId();
      
  	}
  	else{
  		echo "failed to add".json_encode($insert->errorInfo());
  	}
  }

  // update info 
   function updateAdmin($arr){

     $name = $arr['name'];
    $category = $arr['category'];
    $phone = $arr['phone'];
    $national_id= $arr['nid'];
    $sector = $arr['sector'];
    $apssword = $arr['password'];
    $done_by = $arr['done_by'];
    $id = $arr['id'];

    
  
  $upd=$this->conn->prepare("UPDATE admin set name=:name,category=:cat,phone=:phone,nid=:nid,sector=:sec,password=:password,done_by=:done where id=:i");

  $upd->execute(array('name'=>$name,'cat'=>$category,'phone'=>$phone,'nid'=>$nid,'sec'=>$sector,'password'=>$password,'done'=>$done_by,'i'=>$id));

  if($upd->rowCount()>0){
  	echo "database updated";

  }
 else {
 	echo "failed to update".json_encode($upd->errorInfo());
 } 
   }

   
   function deleteAdmin($id){
   	$del = $this->conn->prepare("DELETE from admin where id=:i");
   	$del->execute(array('i' =>$id));
   	if ($del){
   		echo "deleted succeffully";
   	}
    else{
     echo "failed to delete".json_encode($del->errorInfo());
   }
    }
    
   function getAdminSectors($category,$sector){
    $del = $this->conn->prepare("DELETE from admin where category=:cat AND sector=:sec");
    $del->execute(array('cat' =>$category,'sec' =>$sector));
    if ($del){
      echo "fetch successful";
    }
    else{
     echo "failed to fetch".json_encode($del->errorInfo());
   }
    }

    function All_Admin(){
      $getall = $this->conn->prepare("SELECT * from admin");
      $getall->execute();
      $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;   
    }

}

?>