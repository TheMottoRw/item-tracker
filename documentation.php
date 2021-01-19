{
	"info": {
		"_postman_id": "2f240385-fe11-4c59-9f34-8dfec5dff7b0",
		"name": "document",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
	},
	"item": [
		{
			"name": "insert_document",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "formdata",
					"formdata": [
						{
							"key": "category",
							"value": "declareDocument",
							"type": "text"
						},
						{
							"key": "docType",
							"value": "paspport",
							"type": "text"
						},
						{
							"key": "docID",
							"value": "pc1234",
							"type": "text"
						},
						{
							"key": "name",
							"value": "louis",
							"type": "text"
						},
						{
							"key": "phone",
							"value": "0783621452",
							"type": "text"
						},
						{
							"key": "gender",
							"value": "male",
							"type": "text"
						},
						{
							"key": "status",
							"value": "available",
							"type": "text"
						}
					]
				},
				"url": {
					"raw": "http://localhost/www/documentLost/access_method/document_access_method.php?catgetory",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"path": [
						"www",
						"documentLost",
						"access_method",
						"document_access_method.php"
					],
					"query": [
						{
							"key": "catgetory",
							"value": null
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "update document",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "formdata",
					"formdata": [
						{
							"key": "category",
							"value": "updatedocument",
							"type": "text"
						},
						{
							"key": "docType",
							"value": "passport",
							"type": "text"
						},
						{
							"key": "document_id",
							"value": "1234",
							"type": "text"
						},
						{
							"key": "name",
							"value": "jabiro",
							"type": "text"
						},
						{
							"key": "phone",
							"value": "0788536278",
							"type": "text"
						},
						{
							"key": "gender",
							"value": "male",
							"type": "text"
						},
						{
							"key": "status",
							"value": "available",
							"type": "text"
						},
						{
							"key": "id",
							"value": "2",
							"type": "text"
						}
					]
				},
				"url": {
					"raw": "http://localhost/www/documentLost/access_method/document_access_method.php?catgetory",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"path": [
						"www",
						"documentLost",
						"access_method",
						"document_access_method.php"
					],
					"query": [
						{
							"key": "catgetory",
							"value": null
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "delete document",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost/www/documentLost/access_method/document_access_method.php?category=deletedocument&phone=0788536278",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"path": [
						"www",
						"documentLost",
						"access_method",
						"document_access_method.php"
					],
					"query": [
						{
							"key": "category",
							"value": "deletedocument"
						},
						{
							"key": "phone",
							"value": "0788536278"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "get document",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost/www/documentLost/access_method/document_access_method.php?category=getdocument&phone=0783061718",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"path": [
						"www",
						"documentLost",
						"access_method",
						"document_access_method.php"
					],
					"query": [
						{
							"key": "category",
							"value": "getdocument"
						},
						{
							"key": "phone",
							"value": "0783061718"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "active_document",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost/www/documentLost/access_method/document_access_method.php?category=active_document",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"path": [
						"www",
						"documentLost",
						"access_method",
						"document_access_method.php"
					],
					"query": [
						{
							"key": "category",
							"value": "active_document"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "deleted_document",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost/www/documentLost/access_method/document_access_method.php?category=deleted_document",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"path": [
						"www",
						"documentLost",
						"access_method",
						"document_access_method.php"
					],
					"query": [
						{
							"key": "category",
							"value": "deleted_document"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "All_documents",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost/www/documentLost/access_method/document_access_method.php?category=All_documents",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"path": [
						"www",
						"documentLost",
						"access_method",
						"document_access_method.php"
					],
					"query": [
						{
							"key": "category",
							"value": "All_documents"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "insertion of user",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "formdata",
					"formdata": [
						{
							"key": "category",
							"value": "insertUser",
							"type": "text"
						},
						{
							"key": "name",
							"value": "kajyibwami",
							"type": "text"
						},
						{
							"key": "password",
							"value": "kazitunga",
							"type": "text"
						},
						{
							"key": "phone",
							"value": "0789654123",
							"type": "text"
						}
					]
				},
				"url": {
					"raw": "http://localhost/www/documentLost/access_method/user_access.php?catgetory",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"path": [
						"www",
						"documentLost",
						"access_method",
						"user_access.php"
					],
					"query": [
						{
							"key": "catgetory",
							"value": null
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "update user",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "formdata",
					"formdata": [
						{
							"key": "name",
							"value": "igor",
							"type": "text"
						},
						{
							"key": "passowrd",
							"value": "android",
							"type": "text"
						},
						{
							"key": "phone",
							"value": "0783061718",
							"type": "text"
						},
						{
							"key": "id",
							"value": "1",
							"type": "text"
						},
						{
							"key": "category",
							"value": "updateUser",
							"type": "text"
						}
					]
				},
				"url": {
					"raw": "http://localhost/www/documentLost/access_method/user_access.php?catgetory",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"path": [
						"www",
						"documentLost",
						"access_method",
						"user_access.php"
					],
					"query": [
						{
							"key": "catgetory",
							"value": null
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "get user",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost/www/documentLost/access_method/user_access.php?category=getUser&phone=0783061718",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"path": [
						"www",
						"documentLost",
						"access_method",
						"user_access.php"
					],
					"query": [
						{
							"key": "category",
							"value": "getUser"
						},
						{
							"key": "phone",
							"value": "0783061718"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "get users",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost/www/documentLost/access_method/user_access.php?category=getUsers",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"path": [
						"www",
						"documentLost",
						"access_method",
						"user_access.php"
					],
					"query": [
						{
							"key": "category",
							"value": "getUsers"
						}
					]
				}
			},
			"response": []
		}
	]
}