package com.example.afinal;

public class Coin {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(String market_cap) {
        this.market_cap = market_cap;
    }

    public String getVolume_24h() {
        return volume_24h;
    }

    public void setVolume_24h(String volume_24h) {
        this.volume_24h = volume_24h;
    }

    public String getDelta_24h() {
        return delta_24h;
    }

    public void setDelta_24h(String delta_24h) {
        this.delta_24h = delta_24h;
    }

    private String name;
    private String symbol;
    private String price;
    private int rank;
    private String market_cap;
    private String volume_24h;
    private String delta_24h;

}

