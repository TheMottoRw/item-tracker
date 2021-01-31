package com.example.itemtracker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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


public class DocumentTypeView extends Fragment {

    private FloatingActionButton fabaddDocument;
    private RecyclerView docRecycle;
    private RecyclerView.LayoutManager layoutManager;
    public Context ContextCtx, ctx;
    private ProgressDialog pgdialog;
    private Helper helper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_document_type_view, container, false);
        ctx = view.getContext();


        fabaddDocument = view.findViewById(R.id.fab_add);
        helper = new Helper(ctx);
        pgdialog = new ProgressDialog(ctx);
        pgdialog.setMessage(ctx.getString(R.string.processrequest));


        // for recycle view
        docRecycle = view.findViewById(R.id.docRecycleView);
        layoutManager = new LinearLayoutManager(view.getContext());
        docRecycle.setLayoutManager(layoutManager);

        fabaddDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addReservation = new Intent(view.getContext(), DocumentTypeForm.class);
                startActivity(addReservation);
            }
        });
        fetch_Data();

        Toast.makeText(ContextCtx, "Doctype view", Toast.LENGTH_LONG).show();
        return view;
    }

    public void onAttach(Context ctx) {
        super.onAttach(ctx);
        ContextCtx = ctx;


    }

    @Override
    public void onResume() {
        super.onResume();
        fetch_Data();
    }

    private void fetch_Data() {
        pgdialog.show();
        RequestQueue queue = Volley.newRequestQueue(ContextCtx);
        String url = helper.host + "/document_type_access.php?category=get";
        Log.d("Req", url);
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Resp", response);
                        pgdialog.dismiss();

                        try {

                            JSONArray array = new JSONArray(response); // convert string to json array
                            if (array.length() > 0) {
//
                                DocumentTypeAdapter adaptExpenses = new DocumentTypeAdapter(ContextCtx, array);
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
                Toast.makeText(ContextCtx, "error " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}


