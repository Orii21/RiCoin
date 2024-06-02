package com.example.afinal;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarketFragment extends Fragment {

    private RecyclerView recyclerView;
    private CoinAdapter coinAdapter;
    private ImageView iv_search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coin_list, container, false);
        recyclerView = view.findViewById(R.id.rv_coin);
        iv_search = view.findViewById(R.id.iv_search);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fetchCoins();

        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new SearchFragment());
            }
        });
        return view;
    }
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void fetchCoins() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://coinlib.io/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ApiResponse> call = apiService.getCoinList("1672e16d618099e7", "USDT", 1, "volume_desc");

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    List<Coin> coins = response.body().getCoins();
                    if (coins.size() > 10) {
                        coins = coins.subList(0, 10);
                    }
                    coinAdapter = new CoinAdapter(coins);
                    recyclerView.setAdapter(coinAdapter);
                } else {
                    // Log pesan atau lakukan penanganan lain untuk kasus gagal
                    Log.e("MarketFragment", "Gagal mendapatkan data dari API");
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("MarketFragment", "Gagal melakukan panggilan ke API", t);
            }
        });
    }
}




