package com.example.itemtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

public class adminForm extends AppCompatActivity {

    public EditText category,admin_name,phone,nationalid,admin_sector,admin_password;
    public Button registerAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_form);

     category = findViewById(R.id.cat);
     admin_name = findViewById(R.id.adminname);
     phone = findViewById(R.id.adminphone);
     nationalid = findViewById(R.id.nid);
     admin_sector = findViewById(R.id.adminSector);
     admin_password = findViewById(R.id.adminpassword);

     registerAdmin = findViewById(R.id.registerAdmin);

     registerAdmin.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

         }
     });

        void insertAdmin() {

            RequestQueue queue = Volley.newRequestQueue(adminForm.this);
            String url = "";
            Log.d("Req", url);
// Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            Snackbar.make(insertAdmin, response, Snackbar.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "error " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

// Add the request to the RequestQueue.
            queue.add(stringRequest);


        }


    }
}