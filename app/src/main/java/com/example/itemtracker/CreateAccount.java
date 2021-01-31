package com.example.itemtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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

public class CreateAccount extends AppCompatActivity {

    public TextView name,phone,password,sector;
    public Button register;
    public RadioButton radMale,radFemale;
    private RadioGroup group;
    private ProgressDialog pgdialog;
    private Helper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


        pgdialog = new ProgressDialog(this);
        pgdialog.setMessage(getString(R.string.processrequest));
        helper = new Helper(this);

        register  = findViewById(R.id.register);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.residentpassword);
        sector = findViewById(R.id.residentSector);
        group = findViewById(R.id.radiogroup);
        radMale = findViewById(R.id.male);
        radFemale = findViewById(R.id.female);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }
    public void createAccount() {

        RequestQueue queue = Volley.newRequestQueue(CreateAccount.this);
        String url = helper.host+"/user_access.php";
        String gender = radMale.isChecked()?"Male":"Female";

        Log.d("Req", url+"=="+helper.getDataValue("regno"));
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(obj.getString("status").equals("ok")){
                                helper.showToast("Account successful created");
                                startActivity(new Intent(CreateAccount.this,Login.class));
                            }else{
                                helper.showToast("Failed to create account");
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
                params.put("name", name.getText().toString().trim());
                params.put("phone", phone.getText().toString().trim());
                params.put("sector", sector.getText().toString().trim());
                params.put("gender", gender);
                params.put("password", password.getText().toString().trim());
                return params;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}