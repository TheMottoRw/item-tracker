package com.example.itemtracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends Fragment {

    private FloatingActionButton fabdecla;
    private RecyclerView declaRecycle;
    private RecyclerView.LayoutManager layoutManager;
    public Context ContextCtx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.activity_main, container, false);


        fabdecla = view.findViewById(R.id.fab_decla);


        // for recycle view
        declaRecycle = view.findViewById(R.id.declaRecycleView);
        layoutManager = new LinearLayoutManager(view.getContext());
        declaRecycle.setLayoutManager(layoutManager);


        fabdecla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addReservation  = new Intent (view.getContext(), DeclarationForm.class);
                startActivity(addReservation);
            }
        });

        Toast.makeText(ContextCtx,"Doctype view",Toast.LENGTH_LONG).show();
        return view;
    }

    public void onAttach(Context ctx){
        super.onAttach(ctx);
        ContextCtx = ctx;



    }
    @Override
    public void onResume() {
        super.onResume();
//        fetch_Data();
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
                                DeclarationAdapter adaptExpenses = new DeclarationAdapter(ContextCtx, array);

                                declaRecycle.setAdapter(adaptExpenses);


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


