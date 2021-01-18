<?php
class users{ 
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

 // insert user
 function insertUser($arr){

    $name = $arr['name'];
    $phone = $arr['phone'];
    $password = $arr['password'];
   
    $insert = $this->conn->prepare("INSERT INTO users set name=:n,phone=:phone,password=:password");

    $insert->execute(array('n'=>$name,'phone'=>$phone,'password'=>$password));
    if($insert->rowCount()>0){
      echo "you added activity ".$this->conn->lastInsertId();
      
    }
    else{
      echo "failed to add".json_encode($insert->errorInfo());
    }
  }

  // update user 
   function updateUser($arr){

    $name = $arr['name'];
    $phone = $arr['phone'];
    $password = $arr['password'];
    $id = $arr['id'];
    
  
  $upd=$this->conn->prepare("UPDATE users set name=:n,phone=:phone,password=:password where id=:i");

  $upd->execute(array('n'=>$name,'phone'=>$phone,'password'=>$password,'i'=>$id));

  if($upd->rowCount()>0){
    echo "database updated";

  }
 else {
  echo "failed to update".json_encode($upd->errorInfo());
 } 
   }
    
    function login($name,$password){
      $getall = $this->conn->prepare("SELECT id,name,phone from users where name=:n AND password=:p");
      $getall->execute(array('n' =>$name ,'p'=>$password));
      $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;   
    }
    
    // fetch users
    function getUsers(){
    	$getall = $this->conn->prepare("SELECT * from users");
    	$getall->execute(array());
    	$data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;   
    }

// fetch user
    function getUser($phone){
      $getall = $this->conn->prepare("SELECT * from users where phone=:p");
      $getall->execute(array('p'=>$phone));
      $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;   
    }
   

}

?>