<?php
include_once "Database.php";

class Residents
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
    $password = base64_encode($arr['password']);
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
    $sector = $arr['sector'];
    $phone = $arr['phone'];
    $id = $arr['id'];
    
  
  $upd=$this->conn->prepare("UPDATE residents set name=:n,phone=:phone,sector=:sect where id=:i");

  $upd->execute(array('n'=>$name,'phone'=>$phone,'sect'=>$sector,'i'=>$id));

 if ($upd->rowCount() == 0) {
      $response = ['status' => 'fail', 'message' => "failed to update user", 'id' => $id, 'error' => $upd->errorInfo()];
        }
        return $response;
    }


    // fetch users
    function getUsers(){
    	$getall = $this->conn->prepare("SELECT * from residents");
    	$getall->execute(array());
    	$data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;   
    }

// fetch user
    function getUser($id){
      $getall = $this->conn->prepare("SELECT * from residents where id=:i");
      $getall->execute(array('i'=>$id));
      $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;   
    }
    function delete($id){
        $response = ['status' => 'ok', 'message' => "Successful deleted resident", 'id' => 0];

        $del = $this->conn->prepare("DELETE from residents where id=:i");
        $del->execute(array('i' =>$id));
        if ($del->rowCount() == 0) {
            $response = ['status' => 'fail', 'message' => "Failed to delete", 'id' => $id, "error" => $del->errorInfo()];
        }
        return $response;
    }



}

?>