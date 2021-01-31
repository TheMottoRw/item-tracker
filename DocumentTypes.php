<?php
include_once "Database.php";

class DocumentTypes
{
    private $conn;

    function __construct()
    {
        $db = new Database();
        $this->conn = $db->getInstance();
    }


    function insertDocumentType($arr)
    {
        $response = ['status' => 'ok', 'message' => "Successful inserted", 'id' => 0];

        $document_name = $arr['document_name'];


        $insert = $this->conn->prepare("INSERT INTO document_types set document_name=:docname");

        $insert->execute(array('docname' => $document_name));
        if ($insert->rowCount() > 0) {
            $response['message'] = "you added document type ";
            $response['id'] = $this->conn->lastInsertId();
        } else {
            $response = ['status' => 'fail', 'message' => "failed to add document type", 'error' => $insert->errorInfo()];
        }
        return $response;
    }

    // update info
    function updatedocumentType($arr)
    {
        $response = ['status' => 'ok', 'message' => "Document type succesful updated", 'id' => $arr['id']];

        $document_name = $arr['document_name'];
        $id = $arr['id'];

        $upd = $this->conn->prepare("UPDATE document_types set document_name=:docname where doc_id=:i");

        $upd->execute(array('docname' => $document_name, 'i' => $id));

        if ($upd->rowCount() == 0) {
            $response = ['status' => 'fail', 'message' => "failed to update document type", 'id' => $id, 'error' => $upd->errorInfo()];
        }
        return $response;
    }

    // delete documentType
    function deletedocumentType($id)
    {
        $response = ['status' => 'ok', 'message' => "Successful deleted document type", 'id' => 0];
        $del = $this->conn->prepare("DELETE from document_types where doc_id=:i");
        $del->execute(array('i' => $id));
        if ($del->rowCount() == 0) {
            $response = ['status' => 'fail', 'message' => "Failed to delete", 'id' => $id, "error" => $del->errorInfo()];
        }
        return $response;
    }


    function getById($arr)
    {
        $getall = $this->conn->prepare("SELECT * from document_types WHERE doc_id=:id");
        $getall->execute(['id'=>$arr['id']]);
        $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;
    }
    function All_documentsType()
    {
        $getall = $this->conn->prepare("SELECT * from document_types");
        $getall->execute();
        $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;
    }


}

?>