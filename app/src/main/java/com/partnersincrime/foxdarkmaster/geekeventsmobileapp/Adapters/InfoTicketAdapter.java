package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Interfaces.InfoTicketClickListener;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Models.TicketModel;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;


public class InfoTicketAdapter extends RecyclerView.Adapter<InfoTicketAdapter.ViewHolder> {

    private final ArrayList<TicketModel> TicketData;
    private final InfoTicketClickListener mListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mNameTv;
        private final TextView mDescriptionTv;
        private final TextView mPriceTv;
        private final TextView mButtonTv;

        public ViewHolder(View view) {
            super(view);
            mNameTv = (TextView) view.findViewById(R.id.card_ticket_type_name);
            mDescriptionTv = (TextView) view.findViewById(R.id.card_ticket_type_description);
            mPriceTv = (TextView) view.findViewById(R.id.card_ticket_type_price);
            mButtonTv = (TextView) view.findViewById(R.id.card_read_more);
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

        public TextView getButtonView() {
            return mButtonTv;
        }
    }

    public InfoTicketAdapter(ArrayList<TicketModel> dataSet, InfoTicketClickListener listener) {
        TicketData = dataSet;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_ticket_type, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final TicketModel mDataModel = TicketData.get(position);
        NumberFormat mCurrencyFormat = NumberFormat.getCurrencyInstance();
        mCurrencyFormat.setCurrency(Currency.getInstance(mDataModel.getCurrency()));
        viewHolder.getNameView().setText(mDataModel.getName());
        viewHolder.getDescriptionView().setText(mDataModel.getDescription());
        viewHolder.getPriceView().setText(mCurrencyFormat.format(mDataModel.getPrice()));
        viewHolder.getButtonView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick( mDataModel.getBuyAddress() );
            }
        });
    }

    @Override
    public int getItemCount() {
        return TicketData.size();
    }
}
