package com.example.va3prog2.repositories;

import com.example.va3prog2.models.Gift;

import java.util.ArrayList;
import java.util.List;

public class GiftsRepository {
    private List<Gift> giftList;
    private static GiftsRepository instance;

    private GiftsRepository() {
        this.giftList = new ArrayList<>();
    }
    public static GiftsRepository getInstance() {
        if (instance == null) {
            instance = new GiftsRepository();
        }
        return instance;
    }

    public void addGift(Gift gift) {
        giftList.add(gift);
    }

    public List<Gift> getGiftList() {
        return giftList;
    }

    public void setGiftList(List<Gift> giftList) {
        this.giftList = giftList;
    }



}
