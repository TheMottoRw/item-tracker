<?php
include_once "Database.php";

class documentType
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


        $insert = $this->conn->prepare("INSERT INTO document_type set document_name=:docname");

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
        $id = $arr['doc_id'];

        $upd = $this->conn->prepare("UPDATE document_type set document_name=:docname where doc_id=:i");

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
        $del = $this->conn->prepare("DELETE from document_type S where id=:i");
        $del->execute(array('i' => $id));
        if ($del->rowCount() == 0) {
            $response = ['status' => 'fail', 'message' => "Failed to delete", 'id' => $id, "error" => $del->errorInfo()];
        }
    }


    function All_documentsType()
    {
        $getall = $this->conn->prepare("SELECT * from documents");
        $getall->execute();
        $data = $getall->fetchAll(PDO::FETCH_ASSOC);
        return $data;
    }


}

?>