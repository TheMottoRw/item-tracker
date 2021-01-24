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

public class documentTypeAdapter extends RecyclerView.Adapter<documentTypeAdapter.ViewHolder> {

    private JSONArray documentDeclared;
    public LinearLayout ln;
    public Context contx;


    @Override
    public documentTypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        ln = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.documenttype_text_view, parent, false);
        documentTypeAdapter.ViewHolder vh = new documentTypeAdapter.ViewHolder(ln);
        return vh;
    }



    public documentTypeAdapter(Context context, JSONArray declaredData) {
        documentDeclared = declaredData;
        contx = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView Tvdocname, Tvaddedby;
        public ViewHolder(LinearLayout lnout) {
            super(lnout);

            Tvdocname = lnout.findViewById(R.id.documentname);
            Tvaddedby = lnout.findViewById(R.id.addby);


        }
    }

    @Override
    public void onBindViewHolder(@NonNull documentTypeAdapter.ViewHolder holder, int position) {
        try {
            JSONObject object = documentDeclared.getJSONObject(position);

            holder.Tvdocname.setText(object.getString("document_name"));
            holder.Tvaddedby.setText(object.getString("added_by"));


        }catch(JSONException ex){

        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
