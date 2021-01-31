<?php
include_once "Database.php";

class declaredDocument
{
    private $conn;

    function __construct()
    {
        $db = new Database();
        $this->conn = $db->getInstance();
    }


    // declare document
    function declareDocument($arr)
    {
        $response = ['status' => 'ok', 'message' => "Successful declared ", 'id' => 0];

        $doc_type = $arr['document_type'];
        $document_id = $arr['document_id'];
        $resident = $arr['resident'];


        $insert = $this->conn->prepare("INSERT INTO declared_documents set document_type=:doc,document_id=:docid,resident=:resi");

        $insert->execute(array('doc' => $doc_type, 'docid' => $document_id, 'resi' => $resident));
        if ($insert->rowCount() > 0) {
            $response['message'] = "you declared a document ";
            $response['id'] = $this->conn->lastInsertId();
        } else {
            $response = ['status' => 'fail', 'message' => "failed to declare a document", 'error' => $insert->errorInfo()];
        }
        return $response;
    }

    // update info
    function updatedocument($arr)
    {

        $response = ['status' => 'ok', 'message' => "Successful updated ", 'id' => 0];

        $doc_type = $arr['document_type'];
        $document_id = $arr['document_id'];
        $resident = $arr['resident'];
        $added_by = $arr['added_by'];
        $given_by = $arr['given_by'];
        $id = $arr['id'];


        $upd = $this->conn->prepare("UPDATE declared_documents set document_type=:doc,document_id=:docid,resident=:resi,added_by=:add,given_by=:given where id=:i");

        $upd->execute(array('doc' => $doc_type, 'docid' => $document_id, 'resi' => $resident, 'add' => $added_by, 'given' => $given_by, 'i' => $id));

        if ($upd->rowCount() == 0) {
            $response = ['status' => 'fail', 'message' => "failed to update declared document", 'id' => $id, 'error' => $upd->errorInfo()];
        }
        return $response;
    }

    // delete document
    function deleteDocument($id)
    {

        $response = ['status' => 'ok', 'message' => "Successful deleted document type", 'id' => 0];

        $del = $this->conn->prepare("UPDATE declared_documents SET status=:stat where id=:i ");
        $del->execute(array('stat' => "deleted", 'i' => $id));
        if ($del->rowCount() == 0) {
            $response = ['status' => 'fail', 'message' => "Failed to delete", 'id' => $id, "error" => $del->errorInfo()];
        }
        return $response;
    }

    //  retrieve 
    function getdocument($id)
    {
        $getall = $this->conn->prepare("SELECT r.name,r.phone,r.gender,r.sector,dt.document_name,dd.* from declared_documents dd INNER JOIN document_types dt ON dd.document_type=dt.doc_id INNER JOIN residents r on dd.resident = r.id where dd.id=:i");
        $getall->execute(array('i' => $id));
        $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;
    }

    function getByStatus($arr)
    {
        $getall = $this->conn->prepare("SELECT r.name,r.phone,r.gender,r.sector,dt.document_name,dd.* from declared_documents dd INNER JOIN document_types dt ON dd.document_type=dt.doc_id INNER JOIN residents r on dd.resident = r.id where dd.status=:status");
        $getall->execute(['status'=>$arr['status']]);
        $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;
    }

    function getBySerial($arr)
    {
        $getall = $this->conn->prepare("SELECT r.name,r.phone,r.gender,r.sector,dt.document_name,dd.* from declared_documents dd INNER JOIN document_types dt ON dd.document_type=dt.doc_id INNER JOIN residents r on dd.resident = r.id where dd.document_id=:docid");
        $getall->execute(['docid'=>$arr['serial']]);
        $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;
    }
    function getBySector($arr){
        $getall = $this->conn->prepare("SELECT r.name,r.phone,r.gender,r.sector,dt.document_name,dd.* from declared_documents dd INNER JOIN document_types dt ON dd.document_type=dt.doc_id INNER JOIN residents r on dd.resident = r.id where r.sector=:sector");
        $getall->execute(['sector'=>$arr['sector']]);
        $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;
    }

    function All_documents()
    {
        $getall = $this->conn->prepare("SELECT r.name,r.phone,r.gender,r.sector,dt.document_name,dd.* from declared_documents dd INNER JOIN document_types dt ON dd.document_type=dt.doc_id INNER JOIN residents r on dd.resident = r.id");
        $getall->execute();
        $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;
    }


}

?>