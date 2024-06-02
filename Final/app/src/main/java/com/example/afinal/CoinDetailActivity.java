package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CoinDetailActivity extends AppCompatActivity {
    private ImageView iv_chart, iv_logo;
    private TextView tv_name, tv_symbol, tv_price, tv_delta, tv_marketcap, tv_rank;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_detail);

        iv_logo = findViewById(R.id.iv_logo);
        iv_chart = findViewById(R.id.iv_chart);
        tv_name = findViewById(R.id.tv_name);
        tv_symbol = findViewById(R.id.tv_symbol);
        tv_price = findViewById(R.id.tv_price);
        tv_delta = findViewById(R.id.tv_delta24h);
        tv_marketcap = findViewById(R.id.tv_marketcap);
        tv_rank = findViewById(R.id.tv_rank);

        // Mendapatkan data dari intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        String symbol = intent.getStringExtra("Symbol");
        String price = intent.getStringExtra("Price");
        String delta = intent.getStringExtra("Delta24h");
        String marketcap = intent.getStringExtra("MarketCap");
        int logo = intent.getIntExtra("Logo",0);
        int rank = intent.getIntExtra("Rank", 0);
        int chart = intent.getIntExtra("Chart", R.drawable.ic_launcher_foreground);

        // Menampilkan data
        iv_chart.setImageResource(chart);
        tv_name.setText(name);
        tv_symbol.setText(symbol);
        tv_price.setText(price);
        tv_delta.setText(delta);
        tv_marketcap.setText(marketcap);
        iv_logo.setImageResource(logo);
        tv_rank.setText("#"+rank);
    }
}
