package com.example.afinal;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.CoinViewHolder> {
    private List<Coin> coins;

    public CoinAdapter(List<Coin> coins) {
        this.coins = coins;
    }

    @NonNull
    @Override
    public CoinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coin, parent, false);
        return new CoinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoinViewHolder holder, int position) {
        Coin coin = coins.get(position);
        holder.tv_name.setText(coin.getSymbol());
        String priceString = String.valueOf(coin.getPrice());

        // Mengatur jumlah karakter maksimum yang akan ditampilkan
        int maxLength = 5;
        if (priceString.length() > maxLength) {
            priceString = priceString.substring(0, maxLength);
        }

        holder.tv_price.setText("$" + priceString);
        holder.tv_delta24h.setText(coin.getDelta_24h() + "%");

        String marketcapString = String.valueOf(coin.getMarket_cap());

        int maxLength1 = 8;
        if (marketcapString.length() > maxLength1) {
            marketcapString = marketcapString.substring(0, maxLength1);
        }

        holder.tv_marketcap.setText("$" + marketcapString);
        holder.tv_rank.setText(String.valueOf(position + 1));
        holder.iv_coin.setImageResource(coinImages[position]);
        String finalPriceString = priceString;
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), CoinDetailActivity.class);
            intent.putExtra("Name", coin.getName());
            intent.putExtra("Symbol", coin.getSymbol());
            intent.putExtra("Price", "$" + finalPriceString);
            intent.putExtra("Delta24h", coin.getDelta_24h() + "%");
            intent.putExtra("Rank", coin.getRank());
            intent.putExtra("MarketCap", coin.getMarket_cap());
            intent.putExtra("Chart", position < coinImages.length ? coinChart[position] : R.drawable.icons8_bitcoin_48);
            intent.putExtra("Logo", position < coinImages.length ? coinImages[position] : R.drawable.icons8_bitcoin_48);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return Math.min(coins.size(), 10);
    }

    static class CoinViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_price, tv_delta24h, tv_marketcap, tv_rank;
        ImageView iv_coin;

        CoinViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_coinname);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_delta24h = itemView.findViewById(R.id.tv_delta24h);
            tv_marketcap = itemView.findViewById(R.id.tv_marketcap);
            tv_rank = itemView.findViewById(R.id.tv_rank);
            iv_coin = itemView.findViewById(R.id.iv_coin);
        }
    }

    private int[] coinImages = {R.drawable.icons8_tether_48, R.drawable.icons8_ethereum_48, R.drawable.icons8_bitcoin_48, R.drawable.icons8_usdc_64, R.drawable.icons8_solana_64, R.drawable.icons8_bnb_64, R.drawable.icons8_xlm_64, R.drawable.icons8_doge_48, R.drawable.icons8_xrp_64, R.drawable.icons8_chainlink_64};
    private int[] coinChart = {R.drawable.usdt_chart, R.drawable.eth_chart, R.drawable.bitcoin_chart, R.drawable.usdc_chart, R.drawable.sol_chart, R.drawable.bnb_chart, R.drawable.xlm_chart, R.drawable.doge_chart, R.drawable.xrp_chart, R.drawable.link_chart};
}
