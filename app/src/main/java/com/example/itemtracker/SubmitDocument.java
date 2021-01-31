package com.example.itemtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SubmitDocument extends AppCompatActivity {
    private ProgressDialog pgdialog;
    private Helper helper;
    private EditText edtDocumentId;
    private Spinner spnDocumentType;
    private ArrayList documentList,documentListId;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_document);
        pgdialog = new ProgressDialog(this);
        pgdialog.setMessage(getString(R.string.processrequest));
        helper = new Helper(this);

        spnDocumentType = findViewById(R.id.spnDocumentType);
        edtDocumentId = findViewById(R.id.edtDocumentId);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitDocument();
            }
        });

        loadDocumentTypes();
    }
    public void submitDocument() {

        RequestQueue queue = Volley.newRequestQueue(SubmitDocument.this);
        String url = helper.host+"/document_access.php";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(obj.getString("status").equals("ok")){
                                helper.showToast("Lost item subimission done successful");
                                finish();
                            }else{
                                helper.showToast("Failed to submit lost item");
                            }
                        }catch (JSONException ex){
                            Log.d("declareErr",ex.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("category", "declare");
                params.put("document_type", documentListId.get(spnDocumentType.getSelectedItemPosition())+"");
                params.put("document_id", edtDocumentId.getText().toString().trim());
                params.put("done_by", helper.getDataValue("id"));
                return params;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    public void loadDocumentTypes(){

        RequestQueue queue = Volley.newRequestQueue(SubmitDocument.this);
        String url = helper.host+"/document_type_access.php?category=get";
        Log.d("Req", url);
// Request a string response from the provided URL.
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray array) {
                        // Display the first 500 characters of the response string.
                        try {
                            documentList = new ArrayList();
                            documentListId = new ArrayList();
                            for(int i=0;i<array.length();i++){
                                JSONObject obj = array.getJSONObject(i);
                                documentList.add(obj.getString("document_name"));
                                documentListId.add(obj.getString("doc_id"));
                            }
                            ArrayAdapter adapter = new ArrayAdapter<String>(SubmitDocument.this, android.R.layout.simple_spinner_dropdown_item , documentList);
                            spnDocumentType.setAdapter(adapter);

                        }catch (JSONException ex){
                            Log.d("declareErr",ex.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(arrayRequest);
    }

}