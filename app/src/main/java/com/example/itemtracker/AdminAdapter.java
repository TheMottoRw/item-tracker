package com.example.itemtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.ViewHolder> {

    private JSONArray adminDeclared;
    public LinearLayout ln;
    public Context contx;


    @Override
    public AdminAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        ln = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin_text_view, parent, false);
        AdminAdapter.ViewHolder vh = new AdminAdapter.ViewHolder(ln);
        return vh;
    }


    public AdminAdapter(Context context, JSONArray declaredData) {
        adminDeclared = declaredData;
        contx = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView TvadminName, Tvaddedby,Tvsector, Tvphone;
        public ViewHolder(LinearLayout lnout) {
            super(lnout);

            TvadminName = lnout.findViewById(R.id.adminName);
            Tvaddedby = lnout.findViewById(R.id.doneby);
            Tvsector = lnout.findViewById(R.id.sectorAdm);
            Tvphone = lnout.findViewById(R.id.phoneAdm);


        }
    }

    @Override
    public void onBindViewHolder(@NonNull AdminAdapter.ViewHolder holder, int position) {
        try {
            JSONObject object = adminDeclared.getJSONObject(position);


            holder.TvadminName.setText(object.getString("name"));
            holder.Tvaddedby.setText(object.getString("category"));
            holder.Tvsector.setText(object.getString("sector"));
            holder.Tvphone.setText(object.getString("phone"));


        }catch(JSONException ex){

        }
    }

    @Override
    public int getItemCount() {
        return adminDeclared.length();

    }

}
