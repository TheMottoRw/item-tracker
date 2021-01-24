package com.example.itemtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class admin_view extends Fragment { //must extend fragment

    private FloatingActionButton fabaddAdmin;
    private RecyclerView adminRecycle;
    private RecyclerView.LayoutManager layoutManager;
    public Context ContextCtx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.activity_admin_view, container, false);


        fabaddAdmin = view.findViewById(R.id.fab_admin);


        // for recycle view
        adminRecycle = view.findViewById(R.id.adminRecycleView);
        layoutManager = new LinearLayoutManager(view.getContext());
        adminRecycle.setLayoutManager(layoutManager);
        fetch_Data();


        fabaddAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addReservation  = new Intent (view.getContext(), adminForm.class);
                startActivity(addReservation);
            }
        });

        fetch_Data();
        return view;
    }

    public void onAttach(Context ctx){
        super.onAttach(ctx);
        ContextCtx = ctx;



    }
    @Override
    public void onResume() {
        super.onResume();
        fetch_Data();
    }
    private void fetch_Data() {
        RequestQueue queue = Volley.newRequestQueue(ContextCtx);
        String url = "";
        Log.d("Req", url);
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONArray array = new JSONArray(response); // convert string to json array
                            Log.d("Resp",array.toString());
                            if (array.length() > 0) {
//
                                adminAdapter adaptExpenses = new adminAdapter(ContextCtx, array);

                                adminRecycle.setAdapter(adaptExpenses);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ContextCtx, "error " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}

