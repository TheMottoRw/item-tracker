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

public class declarationAdapter extends RecyclerView.Adapter<declarationAdapter.ViewHolder> {

    private JSONArray dataDeclared;
    public LinearLayout ln;
    public Context contx;


    @Override
    public declarationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        ln = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.declaration_text_view, parent, false);
        ViewHolder vh = new ViewHolder(ln);
        return vh;
    }

    public declarationAdapter(Context context, JSONArray declaredData) {
        dataDeclared = declaredData;
        contx = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView Tvdoctype, Tvdocid, Tvdecladate, Tvmyphone,Tvdname;
        public ViewHolder(LinearLayout lnout) {
            super(lnout);

            Tvdname = lnout.findViewById(R.id.dname);
            Tvdecladate = lnout.findViewById(R.id.date);
            Tvdoctype = lnout.findViewById(R.id.dtype);
            Tvmyphone = lnout.findViewById(R.id.myphone);
            Tvdocid = lnout.findViewById(R.id.did);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            JSONObject object = dataDeclared.getJSONObject(position);

            holder.Tvdname.setText(object.getString("name"));
            holder.Tvdecladate.setText(object.getString("declared_at"));
            holder.Tvdoctype.setText(object.getString("document_type"));
            holder.Tvmyphone.setText(object.getString("phone"));
            holder.Tvdocid.setText(object.getString("document_id"));


        }catch(JSONException ex){

        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
