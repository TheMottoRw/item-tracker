package com.example.itemtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

public class declaration_form extends AppCompatActivity {

    public EditText owner_name,decphone,docid;
    public Spinner doctype;
    public Button  declare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration_form);

        owner_name  = findViewById(R.id.owner_name);
        decphone  = findViewById(R.id.decphone);
        doctype  = findViewById(R.id.doctype);
        docid  = findViewById(R.id.docid);
        declare = findViewById(R.id.declare);

        declare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        void declareDocument() {

            RequestQueue queue = Volley.newRequestQueue(declaration_form.this);
            String url = "";
            Log.d("Req", url);
// Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            Snackbar.make(declareDocument, response, Snackbar.LENGTH_SHORT).show();
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