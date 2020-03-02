package com.example.ptuxiakh.model.PlacePackage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpeningHours {

    @JsonProperty("open_now")
    boolean openNow;

    public OpeningHours() {
    }

    public OpeningHours(boolean openNow) {
        this.openNow = openNow;
    }

    public boolean isOpenNow() {
        return openNow;
    }

    public void setOpenNow(boolean openNow) {
        this.openNow = openNow;
    }
}
