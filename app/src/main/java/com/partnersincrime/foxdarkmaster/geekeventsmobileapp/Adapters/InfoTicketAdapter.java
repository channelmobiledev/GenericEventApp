package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Models.TicketModel;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;


public class InfoTicketAdapter extends RecyclerView.Adapter<InfoTicketAdapter.ViewHolder>  {

    private ArrayList<TicketModel> TicketData;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mNameTv;
        private final TextView mDescriptionTv;
        private final TextView mPriceTv;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            mNameTv = (TextView) view.findViewById(R.id.card_ticket_type_name);
            mDescriptionTv = (TextView) view.findViewById(R.id.card_ticket_type_description);
            mPriceTv = (TextView) view.findViewById(R.id.card_ticket_type_price);
        }

        public TextView getNameView() {
            return mNameTv;
        }

        public TextView getDescriptionView() {
            return mDescriptionTv;
        }

        public TextView getPriceView() {
            return mPriceTv;
        }
    }

    public InfoTicketAdapter(ArrayList<TicketModel> dataSet) {
        TicketData = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_ticket_type, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        TicketModel mDataModel = TicketData.get(position);

        NumberFormat mCurrencyFormat = NumberFormat.getCurrencyInstance();
        mCurrencyFormat.setCurrency(Currency.getInstance(mDataModel.getCurrency()));

        viewHolder.getNameView().setText(mDataModel.getName());
        viewHolder.getDescriptionView().setText(mDataModel.getDescription());
        viewHolder.getPriceView().setText(mCurrencyFormat.format(mDataModel.getPrice()));
    }

    @Override
    public int getItemCount() {
        return TicketData.size();
    }
}
