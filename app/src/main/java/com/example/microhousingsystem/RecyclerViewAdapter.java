package com.example.microhousingsystem;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.MessageFormat;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private LayoutInflater inflater;
    private List<Residence> itemList;

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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Residence item = itemList.get(position); // object Item

        viewHolder.residenceID.setText(MessageFormat.format("Residence ID: {0}", item.getResidenceID()));
        viewHolder.residenceAddress.setText(MessageFormat.format("Address: {0}", item.getAddress()));
        viewHolder.residenceAvailability.setText(MessageFormat.format("Availability: {0}", String.valueOf(item.getNumOfUnits())));
        viewHolder.residenceSize.setText(MessageFormat.format("Size per Unit (SqFt): {0}", String.valueOf(item.getSizePerUnit())));
        viewHolder.residenceRental.setText(MessageFormat.format("Monthly Rental (RM): {0}", String.valueOf(item.getMonthlyRental())));
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

        public Button editButton;
        public Button deleteButton;

        public int id;

        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;

            residenceID = itemView.findViewById(R.id.residence_ID);
            residenceAddress = itemView.findViewById(R.id.residence_address);
            residenceAvailability = itemView.findViewById(R.id.residence_available);
            residenceSize = itemView.findViewById(R.id.residence_size);
            residenceRental = itemView.findViewById(R.id.residence_rental);

            editButton = itemView.findViewById(R.id.editButton1);
            deleteButton = itemView.findViewById(R.id.deleteButton);

            editButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int position;
            position = getAdapterPosition();
            Residence item = itemList.get(position);

            switch (v.getId()) {
                case R.id.editButton1:
                    //edit item
                    editItem(item);
                    break;
                case R.id.deleteButton:
                    //delete item
                    deleteItem(item.getResidenceID());
                    break;
            }

        }

        public void deleteItem(final String residenceID) {

            builder = new AlertDialog.Builder(context);

            inflater = LayoutInflater.from(context);

            View view = inflater.inflate(R.layout.confirmation_pop, null);

            Button noButton = view.findViewById(R.id.conf_no_btn);
            Button yesButton = view.findViewById(R.id.conf_yes_btn);

            builder.setView(view);
            dialog = builder.create();
            dialog.show();

            yesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SqliteHelper sqliteHelper = new SqliteHelper(context);
                    sqliteHelper.deleteItem(residenceID);
                    itemList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());

                    dialog.dismiss();
                }
            });

            noButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        private void editItem(final Residence newItem) {

            builder = new AlertDialog.Builder(context);
            inflater = LayoutInflater.from(context);
            final View view = inflater.inflate(R.layout.popup, null);

            Button saveButton;
            final EditText residenceAddress;
            final EditText residenceAvailability;
            final EditText residenceSize;
            final EditText residenceRental;

            residenceAddress = view.findViewById(R.id.residenceAddress);
            residenceAvailability = view.findViewById(R.id.residenceAvailable);
            residenceSize = view.findViewById(R.id.residence_size);
            residenceRental = view.findViewById(R.id.residence_rental);

            saveButton = view.findViewById(R.id.saveButton);
            saveButton.setText("Update");

            residenceAddress.setText(newItem.getAddress());
            residenceAvailability.setText(newItem.getNumOfUnits());
            residenceSize.setText(newItem.getSizePerUnit());
            residenceRental.setText(newItem.getMonthlyRental());

            builder.setView(view);
            dialog = builder.create();
            dialog.show();

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //update our item
                    SqliteHelper sqliteHelper = new SqliteHelper(context);

                    //update items
                    newItem.setAddress(residenceAddress.getText().toString());
                    newItem.setNumOfUnits(residenceAvailability.getText().toString());
                    newItem.setSizePerUnit(residenceSize.getText().toString());
                    newItem.setMonthlyRental(residenceRental.getText().toString());


                    if (!residenceAddress.getText().toString().isEmpty()
                            && !residenceAvailability.getText().toString().isEmpty()
                            && !residenceSize.getText().toString().isEmpty()
                            && !residenceRental.getText().toString().isEmpty()) {

                        sqliteHelper.updateItem(newItem);
                        notifyItemChanged(getAdapterPosition(), newItem); //important!


                    } else {
                        Snackbar.make(view, "Fields Empty",
                                Snackbar.LENGTH_SHORT)
                                .show();
                    }

                    dialog.dismiss();
                }
            });
        }
    }
}
