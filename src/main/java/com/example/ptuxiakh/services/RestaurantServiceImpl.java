package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.general.Restaurant;
import com.example.ptuxiakh.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> getAllPlaces() {
        try{
            List<Restaurant> result = restaurantRepository.findAll();
            if (result != null)
                return result;
        }catch (Exception exc){
            exc.printStackTrace();

        }
        return null;
    }

    @Override
    public Restaurant getSinglePlace(String placeId) throws RuntimeException{
        if (placeId != null) {
            Restaurant result = restaurantRepository.findById(placeId).orElseThrow(() -> new RuntimeException("cant find place!"));
            return result;
        }else {
            throw new NullPointerException("placeId is null");
        }
    }

    @Override
    public Boolean deletePlace(String placeId) {
        try{
            if (placeId != null){
                restaurantRepository.deleteById(placeId);
                return true;
            }
            return false;
        }catch (Exception exc){
            exc.printStackTrace();
        }
        return false;
    }

    @Override
    public Restaurant createNewPlace(Restaurant restaurant) {
        try{
            if (restaurant != null)
                return restaurantRepository.save(restaurant);
        }catch (Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    @Override
    public Restaurant updatePlace(String placeId, Restaurant restaurant) {
        try{
            if (placeId != null && restaurant != null){
                Restaurant oldPlace = restaurantRepository.findById(placeId).orElseThrow(() -> new RuntimeException("cant find place"));
                restaurant.setId(oldPlace.getId());
                Restaurant result = restaurantRepository.save(restaurant);
                return result;
            }else {
                throw new NullPointerException("no placeId or Restaurant provided");
            }
        }catch (Exception exc){
            exc.printStackTrace();
            return null;
        }
    }
}
