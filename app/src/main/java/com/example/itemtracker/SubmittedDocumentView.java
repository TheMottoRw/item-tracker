package com.example.itemtracker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;


public class SubmittedDocumentView extends AppCompatActivity {

    private FloatingActionButton fabsub;
    private RecyclerView subRecycle;
    private RecyclerView.LayoutManager layoutManager;
    public Context ContextCtx;
    private ProgressDialog pgdialog;
    private Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
        setContentView(R.layout.activity_submitted_document_view);
        pgdialog = new ProgressDialog(this);
        pgdialog.setMessage(getString(R.string.processrequest));
        helper = new Helper(this);



        fabsub = findViewById(R.id.fab_sub);


        // for recycle view
        subRecycle = findViewById(R.id.docRecycleView);
        layoutManager = new LinearLayoutManager(this);
        subRecycle.setLayoutManager(layoutManager);
        fetch_Data();


        fabsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addReservation  = new Intent (SubmittedDocumentView.this, DeclarationForm.class);
                startActivity(addReservation);
            }
        });

        fetch_Data();
    }

    @Override
    public void onResume() {
        super.onResume();
        fetch_Data();
    }

    private void fetch_Data() {
        pgdialog.show();
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = helper.host + "/submitted_access.php?category=get";
        Log.d("Req", url);
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pgdialog.dismiss();
                        Log.d("response",response);
                        try {

                            JSONArray array = new JSONArray(response); // convert string to json array
                            if (array.length() > 0) {
//
                                DeclarationAdapter adaptExpenses = new DeclarationAdapter(getApplicationContext(), array);
                                subRecycle.setAdapter(adaptExpenses);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pgdialog.dismiss();
                Toast.makeText(getApplicationContext(), "error " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}


