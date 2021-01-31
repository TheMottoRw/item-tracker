package com.example.itemtracker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SubmittedAdapter extends RecyclerView.Adapter<SubmittedAdapter.ViewHolder> {

    private JSONArray dataDeclared;
    public LinearLayout ln;
    public Context contx;


    @Override
    public SubmittedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        ln = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.submitted_text_view, parent, false);
        ViewHolder vh = new ViewHolder(ln);
        return vh;
    }

    public SubmittedAdapter(Context context, JSONArray declaredData) {
        dataDeclared = declaredData;
        contx = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvDocumentType,tvDocumentId,tvOwnerName,tvStatus,tvGivenDate,tvGivenBy,tvOther;
        public ViewHolder(LinearLayout lnout) {
            super(lnout);

            tvDocumentType = lnout.findViewById(R.id.documenttype);
            tvDocumentId = lnout.findViewById(R.id.documentid);
            tvOwnerName = lnout.findViewById(R.id.ownerName);
            tvStatus = lnout.findViewById(R.id.status);
            tvGivenDate = lnout.findViewById(R.id.givdate);
            tvGivenBy = lnout.findViewById(R.id.given);
            tvOther = lnout.findViewById(R.id.otherdescription);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            JSONObject object = dataDeclared.getJSONObject(position);

            holder.tvDocumentType.setText(object.getString("document_name"));
            holder.tvDocumentId.setText(object.getString("document_id"));
            holder.tvOwnerName.setText(object.getString("name"));
            holder.tvGivenDate.setText(object.getString("given_by").isEmpty()?"":object.getString("given_by"));
            holder.tvGivenBy.setText(object.getString("given_date"));
            holder.tvStatus.setText(object.getString("status"));
            holder.tvOther.setText(object.getString("other_description"));

        }catch(JSONException ex){
            Log.d("jsonerr",ex.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return dataDeclared.length();
    }

}
