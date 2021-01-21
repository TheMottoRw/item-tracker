<?php
include_once "Database.php";

class users
{
    private $conn;

    function __construct()
    {
        $db = new Database();
        $this->conn = $db->getInstance();
    }

 // insert user
 function insertUser($arr){
   $response = ['status' => 'ok', 'message' => "Successful inserted", 'id' => 0];

    $name = $arr['name'];
    $phone = $arr['phone'];
    $password = bson_encode('password');
    $sector = $arr['sector'];
    

   
    $insert = $this->conn->prepare("INSERT INTO residents set name=:n,phone=:phone,password=:password,sector=:sect");

    $insert->execute(array('n'=>$name,'phone'=>$phone,'password'=>$password,'sect'=>$sector));
    if ($insert->rowCount() > 0) {
            $response['message'] = "you added user ";
            $response['id'] = $this->conn->lastInsertId();
        } else {
            $response = ['status' => 'fail', 'message' => "failed to add user", 'error' => $insert->errorInfo()];
        }
        return $response;
    }

  // update user 
   function updateUser($arr){

    $response = ['status' => 'ok', 'message' => "Successful updated", 'id' => 0];

    $name = $arr['name'];
    $phone = $arr['phone'];
    $password = $arr['password'];
    $id = $arr['id'];
    
  
  $upd=$this->conn->prepare("UPDATE residents set name=:n,phone=:phone,password=:password,sector=:sect where id=:i");

  $upd->execute(array('n'=>$name,'phone'=>$phone,'password'=>$password,'sect'=>$sector,'i'=>$id));

 if ($upd->rowCount() == 0) {
      $response = ['status' => 'fail', 'message' => "failed to update user", 'id' => $id, 'error' => $upd->errorInfo()];
        }
        return $response;
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
    function getUser($id){
      $getall = $this->conn->prepare("SELECT * from users where id=:i");
      $getall->execute(array('i'=>$id));
      $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;   
    }
   

}

?>