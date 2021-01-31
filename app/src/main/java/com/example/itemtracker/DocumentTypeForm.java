package com.example.itemtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DocumentTypeForm extends AppCompatActivity {

    public EditText docname;
    public Button insertdocument;
    private ProgressDialog pgdialog;
    private Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_type);
        pgdialog = new ProgressDialog(this);
        pgdialog.setMessage(getString(R.string.processrequest));
        helper = new Helper(this);

       docname = findViewById(R.id.docname);
       insertdocument = findViewById(R.id.submitbutton);

       insertdocument.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               registerDocument();
           }
       });

    }

    public void registerDocument() {

        RequestQueue queue = Volley.newRequestQueue(DocumentTypeForm.this);
        String url = helper.host+"/document_type_access.php";


// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(obj.getString("status").equals("ok")){
                                helper.showToast("Item type registered successful");
                                startActivity(new Intent(DocumentTypeForm.this,Login.class));
                            }else{
                                helper.showToast("Failed to register item type");
                                Log.d("reqErr",obj.getString("error"));
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
                params.put("category", "register");
                params.put("done_by", helper.getDataValue("id"));
                params.put("document_name", docname.getText().toString().trim());
                return params;
            }

        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}