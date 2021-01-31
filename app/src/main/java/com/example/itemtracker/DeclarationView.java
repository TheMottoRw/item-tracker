package com.example.itemtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

import java.util.ArrayList;

public class DeclarationView extends AppCompatActivity {

    private ProgressDialog pgdialog;
    private Helper helper;
    private ArrayList documentList,documentListId;

    private FloatingActionButton fabDeclare;
    private RecyclerView docRecycle;
    private RecyclerView.LayoutManager layoutManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration_view);

        helper = new Helper(this);
        pgdialog = new ProgressDialog(this);
        pgdialog.setMessage(getApplicationContext().getString(R.string.processrequest));


        fabDeclare = findViewById(R.id.fab_declare);
        // for recycle view
        docRecycle = findViewById(R.id.docRecycleView);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        docRecycle.setLayoutManager(layoutManager);

        fabDeclare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addReservation = new Intent(DeclarationView.this, DeclarationForm.class);
                startActivity(addReservation);
            }
        });
        fetch_Data();

    }


    private void fetch_Data() {
        pgdialog.show();
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = helper.host + "/document_access.php?category=get";
        Log.d("Req", url);
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pgdialog.dismiss();

                        try {

                            JSONArray array = new JSONArray(response); // convert string to json array
                            if (array.length() > 0) {
//
                                DeclarationAdapter adaptExpenses = new DeclarationAdapter(getApplicationContext(), array);
                                docRecycle.setAdapter(adaptExpenses);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        // return true so that the menu pop up is opened
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                helper.showToast("Successfull logged out");
                helper.logout();
                finish();
                startActivity(new Intent(DeclarationView.this, Login.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}