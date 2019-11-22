package com.example.microhousingsystem;


import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.snackbar.Snackbar;

import java.text.MessageFormat;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Residence> itemList;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private LayoutInflater inflater;

    public RecyclerViewAdapter(Context context, List<Residence> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.residence_list, viewGroup, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position) {

        Residence item = itemList.get(position); // object Item

        viewHolder.residenceID.setText(MessageFormat.format("Residence ID: {0}", item.getResidenceID()));
        viewHolder.residenceAddress.setText(MessageFormat.format("Address: {0}", item.getAddress()));
        viewHolder.residenceAvailability.setText(MessageFormat.format("Availability: {0}", String.valueOf(item.getNumOfUnits())));
        viewHolder.residenceSize.setText(MessageFormat.format("Size per Unit: {0}", String.valueOf(item.getSizePerUnit())));
        viewHolder.residenceRental.setText(MessageFormat.format("Monthly Rental: {0}", item.getMonthlyRental()));

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView residenceID;
        public TextView residenceAddress;
        public TextView residenceAvailability;
        public TextView residenceSize;
        public TextView residenceRental;
        public int id;

        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;

            residenceID = itemView.findViewById(R.id.residence_ID);
            residenceAddress = itemView.findViewById(R.id.residence_address);
            residenceAvailability = itemView.findViewById(R.id.residence_available);
            residenceSize = itemView.findViewById(R.id.residence_size);
            residenceRental = itemView.findViewById(R.id.residence_rental);
        }

        @Override
        public void onClick(View v) {

            int position;
            position = getAdapterPosition();
            Residence item = itemList.get(position);


        }


    }
}
