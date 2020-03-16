package com.example.ptuxiakh.model.viewModels;

import com.example.ptuxiakh.model.Favourite;

import java.util.ArrayList;

public class UpdateFavouritesRequest {
    ArrayList<Favourite> favourites;

    public UpdateFavouritesRequest() {
    }

    public UpdateFavouritesRequest(ArrayList<Favourite> favourites) {
        this.favourites = favourites;
    }

    public ArrayList<Favourite> getFavourites() {
        return favourites;
    }

    public void setFavourites(ArrayList<Favourite> favourites) {
        this.favourites = favourites;
    }
}
