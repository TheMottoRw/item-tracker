package com.example.itemtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class submittedAdapter extends RecyclerView.Adapter<submittedAdapter.ViewHolder> {

    private JSONArray submitedData;
    public LinearLayout ln;
    public Context contx;


    @Override
    public submittedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        ln = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.submitted_text_view, parent, false);
        submittedAdapter.ViewHolder vh = new submittedAdapter.ViewHolder(ln);
        return vh;
    }

    public submittedAdapter(Context context, JSONArray declaredData) {
        submitedData = declaredData;
        contx = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView Tvdoctype, Tvdocid, Tvgivdate, Tvgivenby,Tvstatus;
        public ViewHolder(LinearLayout lnout) {
            super(lnout);

            Tvgivenby = lnout.findViewById(R.id.given);
            Tvstatus = lnout.findViewById(R.id.status);
            Tvdoctype = lnout.findViewById(R.id.documenttype);
            Tvgivdate = lnout.findViewById(R.id.givdate);
            Tvdocid = lnout.findViewById(R.id.documentid);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull submittedAdapter.ViewHolder holder, int position) {
        try {
            JSONObject object = submitedData.getJSONObject(position);

            holder.Tvgivenby.setText(object.getString("given_by"));
            holder.Tvstatus.setText(object.getString("status"));
            holder.Tvdoctype.setText(object.getString("document_type"));
            holder.Tvgivdate.setText(object.getString("given_date"));
            holder.Tvdocid.setText(object.getString("document_id"));


        }catch(JSONException ex){

        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}

