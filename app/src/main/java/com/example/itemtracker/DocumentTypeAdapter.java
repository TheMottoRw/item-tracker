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

public class DocumentTypeAdapter extends RecyclerView.Adapter<DocumentTypeAdapter.ViewHolder> {

    private JSONArray documentDeclared;
    public LinearLayout ln;
    public Context contx;


    @Override
    public DocumentTypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        ln = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.documenttype_text_view, parent, false);
        DocumentTypeAdapter.ViewHolder vh = new DocumentTypeAdapter.ViewHolder(ln);
        return vh;
    }



    public DocumentTypeAdapter(Context context, JSONArray declaredData) {
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
    public void onBindViewHolder(@NonNull DocumentTypeAdapter.ViewHolder holder, int position) {
        try {
            JSONObject object = documentDeclared.getJSONObject(position);

            holder.Tvdocname.setText(object.getString("document_name"));
            holder.Tvaddedby.setText(object.getString("registration_date").substring(0,10));


        }catch(JSONException ex){

        }
    }

    @Override
    public int getItemCount() {
        return documentDeclared.length();
    }

}
