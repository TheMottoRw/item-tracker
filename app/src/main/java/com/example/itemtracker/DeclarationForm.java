package com.example.itemtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeclarationForm extends AppCompatActivity {

    public EditText docid;
    public Spinner doctype;
    public Button  declare;
    private ProgressDialog pgdialog;
    private Helper helper;
    private ArrayList documentList,documentListId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration_form);
        pgdialog = new ProgressDialog(this);
        pgdialog.setMessage(getString(R.string.processrequest));
        helper = new Helper(this);

        doctype  = findViewById(R.id.doctype);
        docid  = findViewById(R.id.docid);
        declare = findViewById(R.id.declare);

        declare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                declareLostItem();
            }
        });
        loadDocumentTypes();
    }
    public void declareLostItem() {

        RequestQueue queue = Volley.newRequestQueue(DeclarationForm.this);
        String url = helper.host+"/document_access.php?category=get";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(obj.getString("status").equals("ok")){
                                helper.showToast("Declaration done successful");
                                finish();
                            }else{
                                helper.showToast("Failed to declare lost item");
                                Log.d("resErr",response);
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
                params.put("resident", helper.getDataValue("id"));
                params.put("document_type", documentListId.get(doctype.getSelectedItemPosition())+"");
                params.put("document_id", docid.getText().toString().trim());
                return params;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    public void loadDocumentTypes(){

        RequestQueue queue = Volley.newRequestQueue(DeclarationForm.this);
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
                            ArrayAdapter adapter = new ArrayAdapter<String>(DeclarationForm.this, android.R.layout.simple_spinner_dropdown_item , documentList);
                            doctype.setAdapter(adapter);

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