package com.example.kalkulatorku;

@SuppressWarnings("ALL")
public class ItemList {
    private final String mLine1;
    private final String mLine2;
    private final String mLineOperasi;
    private final String mLineHasil;

    public ItemList(String line1, String lineOperasi, String line2, String lineHasil) {
        this.mLine1 = line1;
        this.mLineOperasi = lineOperasi;
        this.mLine2 = line2;
        this.mLineHasil = lineHasil;
    }

    public String getLine1() {
        return mLine1;
    }

    public String getLineOperasi() {
        return mLineOperasi;
    }

    public String getLine2() {
        return mLine2;
    }

    public String getLineHasil() {return mLineHasil;}
}