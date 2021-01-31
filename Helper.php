<?php
include "Database.php";
class Helper{
    function __construct()
    {
        $this->db = new Database();
        $this->conn = $this->db->getInstance();
    }
    function adminExistance(){
        $qy = $this->conn->prepare("SELECT * FROM admin WHERE category=:category");
        $qy->execute(['category'=>'Superadmin']);
        if($qy->rowCount()==0){
            $qy = $this->conn->prepare("INSERT INTO admin SET name=:names,category=:category,phone=:phone,password=:pwd,national_id=:nid");
            $qy->execute(['names'=>'Superadmin','category'=>'Superadmin','phone'=>'0726183049','pwd'=>base64_encode(12345),'nid'=>0]);
            if($qy->rowCount() ==0){
                echo json_encode($qy->errorInfo());
                exit;
            }
        }
    }
    public function login($datas){
//        return $datas;
        $this->adminExistance();
        $response = ['status'=>'ok','data'=>[],'message'=>'Successful logged in'];
        $qy = $this->conn->prepare("SELECT * FROM admin WHERE phone=:phone AND password=:pwd");
        $qy->execute(['phone'=>$datas['phone'],'pwd'=>base64_encode($datas['password'])]);
        if($qy->rowCount()==1){//success
            $response['user_info'] = $qy->fetchAll(PDO::FETCH_ASSOC)[0];
        }else{//check resident
            $qyResident = $this->conn->prepare("SELECT * FROM residents WHERE phone=:phone AND password=:pwd");
            $qyResident->execute(['phone'=>$datas['phone'],'pwd'=>base64_encode($datas['password'])]);
            if($qyResident->rowCount()==1){//success
                $response['user_info'] = $qyResident->fetchAll(PDO::FETCH_ASSOC)[0];
                $response['user_info']['category'] = 'residents';
            }else{
                $response['status'] = 'fail';
                $response['message'] = 'Wrong phone number or password';
            }
        }
        return $response;
    }
}
?>