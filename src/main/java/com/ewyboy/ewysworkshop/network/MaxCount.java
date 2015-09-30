package com.ewyboy.ewysworkshop.network;

public class MaxCount implements com.ewyboy.ewysworkshop.network.IBitCount {
    private int bits;

    public MaxCount(int max) {
        bits = (int)(Math.log10(max + 1) / Math.log10(2)) + 1;
    }

    @Override
    public int getBitCount() {
        return bits;
    }
}
