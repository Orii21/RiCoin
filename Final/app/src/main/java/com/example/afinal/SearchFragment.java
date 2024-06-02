package com.example.afinal;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;

public class SearchFragment extends Fragment {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private CoinAdapter coinAdapter;
    private List<Coin> coinList = new ArrayList<>();
    private List<Coin> filteredCoinList = new ArrayList<>();

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = view.findViewById(R.id.search_view);
        recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        coinAdapter = new CoinAdapter(filteredCoinList);
        recyclerView.setAdapter(coinAdapter);

        setupSearchView();
        fetchCoinData();

        return view;
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterCoins(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterCoins(newText);
                return true;
            }
        });
    }

    private void filterCoins(String query) {
        filteredCoinList.clear();
        for (Coin coin : coinList) {
            if (coin.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredCoinList.add(coin);
            }
        }
        coinAdapter.notifyDataSetChanged();
    }

    private void fetchCoinData() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<ApiResponse> call = apiService.getCoinList("1672e16d618099e7", "USDT", 1, "volume_desc");

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    coinList.clear();
                    List<Coin> allCoins = response.body().getCoins();
                    int limit = Math.min(allCoins.size(), 10);
                    coinList.addAll(allCoins.subList(0, limit));
                    filterCoins(searchView.getQuery().toString());
                    Log.d("CoinData", "Coin list size: " + coinList.size());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Handle the failure case here
            }
        });
    }
}
