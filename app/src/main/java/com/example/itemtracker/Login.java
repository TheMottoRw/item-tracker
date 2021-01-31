package com.example.itemtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class Login extends AppCompatActivity {

    public Button btnLogin;
    public TextView tvCreate;
    private ProgressDialog pgdialog;
    private EditText edtPhone,edtPassword;
    private Helper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pgdialog = new ProgressDialog(this);
        pgdialog.setMessage(getString(R.string.processrequest));
        helper = new Helper(this);
        if(helper.hasSession()) {
            String category = helper.getDataValue("category");
            if(category.equals("Superadmin")) {
                startActivity(new Intent(Login.this, SuperadminNavigation.class));
            }else if(category.equals("Admin")){
                startActivity(new Intent(Login.this,SubmittedDocumentView.class));
            }else{
                startActivity(new Intent(Login.this,DeclarationView.class));
            }
            finish();
        }
        edtPhone = findViewById(R.id.phone);
        edtPassword = findViewById(R.id.password);

        btnLogin  = findViewById(R.id.log_in);
        tvCreate = findViewById(R.id.create);

        tvCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CreateAccount.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    public void login() {
        pgdialog.show();
        final String url = helper.host + "/helper_access.php";
        Log.d("url",url);
        pgdialog.show();
        RequestQueue queue = Volley.newRequestQueue(this);

// prepare the Request
        StringRequest getRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // display response
                        Log.d("loginErr",response);
                        pgdialog.dismiss();
                        try {
                            JSONObject array = new JSONObject(response);
                            if (array.getString("status").equals("ok")) {
                                JSONObject userInfo = array.getJSONObject("user_info");
                                helper.showToast("Login succesful");
                                helper.setSession(userInfo.toString());
                                if(userInfo.getString("category").equals("Superadmin")){
                                    startActivity(new Intent(getApplicationContext(), SuperadminNavigation.class));
                                }else if(userInfo.getString("category").equals("Admin")){
                                    helper.showToast("Admin logged in");
                                    startActivity(new Intent(getApplicationContext(), SubmittedDocumentView.class));
                                }else{//Resident
                                    startActivity(new Intent(getApplicationContext(), DeclarationView.class));
                                    helper.showToast("Resident login");
                                }
                            } else {
                                helper.showToast("Failed to login");
                                Log.d("loginMessage",array.getString("message"));
                            }
                        } catch (JSONException ex) {
                            Log.d("loginErr",ex.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pgdialog.dismiss();
                        Log.e("jsonerr", "JSON Error " + (error != null ? error.getMessage() : ""));
                    }
                }
        ) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("category", "login");
                params.put("phone", edtPhone.getText().toString().trim());
                params.put("password", edtPassword.getText().toString());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", helper.getDataValue("appid"));//put your token here
                return headers;
            }
        };
        ;

// add it to the RequestQueue
        queue.add(getRequest);

    }

}