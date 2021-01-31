<?php
include_once "Database.php";

class admin
{
    private $conn;

    function __construct()
    {
        $db = new Database();
        $this->conn = $db->getInstance();
    }

 // declare 
 function insertAdmin($arr){

    $response = ['status' => 'ok', 'message' => "Admin successful inserted", 'id' => 0];

    $name = $arr['name'];
    $category = $arr['category'];
    $phone = $arr['phone'];
    $national_id= $arr['national_id'];
    $sector = $arr['sector'];
    $password = base64_encode($arr['password']);
    $done_by = $arr['done_by'];

   	$insert = $this->conn->prepare("INSERT INTO admin set name=:names,category=:cat,phone=:phone,national_id=:nid,sector=:sec,password=:password,done_by=:done");

  	$insert->execute(array('names'=>$name,'cat'=>$category,'phone'=>$phone,'nid'=>$national_id,'sec'=>$sector,'password'=>$password,'done'=>$done_by));
  	if($insert->rowCount()>0){
      $response['message'] = "admin added ";
  	  $response['id'] = $this->conn->lastInsertId();
        } else {
            $response = ['status' => 'fail', 'message' => "failed to add admin", 'error' => $insert->errorInfo()];
        }
        return $response;
    }

  // update info 
   function updateAdmin($arr){

$response = ['status' => 'ok', 'message' => "Admin successful updated", 'id' => $arr['id']];

    $name = $arr['name'];
    $category = $arr['category'];
    $phone = $arr['phone'];
    $national_id= $arr['national_id'];
    $sector = $arr['sector'];
    $password = base64_encode('password');
    $done_by = $arr['done_by'];
    $id = $arr['id'];

    
  
  $upd=$this->conn->prepare("UPDATE  admin set name=:name,category=:cat,phone=:phone,national_id=:nid,sector=:sec,password=:password,done_by=:done where id=:i");

  $upd->execute(array('name'=>$name,'cat'=>$category,'phone'=>$phone,'nid'=>$national_id,'sec'=>$sector,'password'=>$password,'done'=>$done_by,'i'=>$id));

  if ($upd->rowCount() == 0) {
            $response = ['status' => 'fail', 'message' => "failed to update admin table", 'id' => $id, 'error' => $upd->errorInfo()];
        }
        return $response;
    }

   
   function deleteAdmin($id){
    $response = ['status' => 'ok', 'message' => "Successful deleted admin", 'id' => 0];

   	$del = $this->conn->prepare("DELETE from admin where id=:i");
   	$del->execute(array('i' =>$id));
   if ($del->rowCount() == 0) {
            $response = ['status' => 'fail', 'message' => "Failed to delete", 'id' => $id, "error" => $del->errorInfo()];
        }
   return $response;
    }
    
   function getAdminSectors($category,$sector){
    $response = ['status' => 'ok', 'message' => "Admin sector"];
    $del = $this->conn->prepare("SELECT * FROM admin where category=:cat AND sector=:sec");
    $del->execute(array('cat' =>$category,'sec' =>$sector));
   $response['data'] = $del->fetchAll(PDO::FETCH_ASSOC);
    return $response;
    }

    function All_Admin(){
      $getall = $this->conn->prepare("SELECT * from admin");
      $getall->execute();
      $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;   
    }
    function getById($datas){
        $getall = $this->conn->prepare("SELECT * from admin WHERE id=:id");
        $getall->execute(['id'=>$datas['id']]);
        $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;
    }

}

?>