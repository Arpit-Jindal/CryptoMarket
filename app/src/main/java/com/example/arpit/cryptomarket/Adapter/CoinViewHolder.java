package com.example.arpit.cryptomarket.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arpit.cryptomarket.R;

public class CoinViewHolder extends RecyclerView.ViewHolder {
    public ImageView coin_icon;
    public TextView coin_symbol,coin_name,coin_price,one_hour_change,twenty_hours_change,seven_days_change;
    public CoinViewHolder(@NonNull View itemView) {
        super(itemView);
        coin_icon = itemView.findViewById(R.id.coin_icon);
        coin_symbol = itemView.findViewById(R.id.coin_symbol);
        coin_price = itemView.findViewById(R.id.priceUsdText);
        one_hour_change = itemView.findViewById(R.id.oneHourText);
        twenty_hours_change = itemView.findViewById(R.id.twentyFourHourText);
        seven_days_change = itemView.findViewById(R.id.sevenDayText);
        coin_name = itemView.findViewById(R.id.coin_name);

    }
}
