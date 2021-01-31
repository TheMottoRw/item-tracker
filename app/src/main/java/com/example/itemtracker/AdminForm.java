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
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AdminForm extends AppCompatActivity {

    public EditText category,admin_name,phone,nationalid,admin_sector,admin_password;
    public Button registerAdmin;
    private ProgressDialog pgdialog;
    private Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_form);
        pgdialog = new ProgressDialog(this);
        pgdialog.setMessage(getString(R.string.processrequest));
        helper = new Helper(this);

     category = findViewById(R.id.cat);
     admin_name = findViewById(R.id.adminname);
     phone = findViewById(R.id.adminphone);
     nationalid = findViewById(R.id.nid);
     admin_sector = findViewById(R.id.adminSector);
     admin_password = findViewById(R.id.adminpassword);

     registerAdmin = findViewById(R.id.btnRegisterAdmin);

     registerAdmin.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             registerAdmin();
         }
     });

    }

    public void registerAdmin() {
        pgdialog.show();
        Toast.makeText(getApplicationContext(),category.getText().toString(),Toast.LENGTH_LONG).show();
        RequestQueue queue = Volley.newRequestQueue(AdminForm.this);
        String url = helper.host+"/admin_access.php";
        Log.d("response",url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pgdialog.dismiss();
                        // Display the first 500 characters of the response string.
                        Log.d("response",response);
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(obj.getString("status").equals("ok")){
                                helper.showToast("Account successful created");
                                startActivity(new Intent(AdminForm.this,Login.class));
                            }else{
                                helper.showToast("Failed to create account");
                                Log.d("reqErr",obj.getString("error"));
                            }
                        }catch (JSONException ex){
                            Log.d("declareErr",ex.getMessage()+" RES "+response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pgdialog.dismiss();
                Toast.makeText(getApplicationContext(), "error " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cate", "register");
                params.put("name", admin_name.getText().toString().trim());
                params.put("phone", phone.getText().toString().trim());
                params.put("sector", admin_sector.getText().toString().trim());
                params.put("gender", nationalid.getText().toString().trim());
                params.put("password", admin_password.getText().toString().trim());
                params.put("category", category.getText().toString().trim());
                params.put("done_by", helper.getDataValue("id"));
                return params;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}