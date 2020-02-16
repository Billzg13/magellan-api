package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.Place;
import com.example.ptuxiakh.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    PlaceRepository placeRepository;

    @Override
    public List<Place> getAllPlaces() {
        try{
            List<Place> result = placeRepository.findAll();
            if (result != null)
                return result;
        }catch (Exception exc){
            exc.printStackTrace();

        }
        return null;
    }

    @Override
    public Place getSinglePlace(String placeId) throws RuntimeException{
        if (placeId != null) {
            Place result = placeRepository.findById(placeId).orElseThrow(() -> new RuntimeException("cant find place!"));
            return result;
        }else {
            throw new NullPointerException("placeId is null");
        }
    }

    @Override
    public Boolean deletePlace(String placeId) {
        try{
            if (placeId != null){
                placeRepository.deleteById(placeId);
                return true;
            }
            return false;
        }catch (Exception exc){
            exc.printStackTrace();
        }
        return false;
    }

    @Override
    public Place createNewPlace(Place place) {
        try{
            if (place == null)
                return null;

            System.out.println("this is the high price: "+ place.getHighPrice());
            place.setDateAdded(new Date());
            return placeRepository.save(place);
        }catch (Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    @Override
    public Place updatePlace(String placeId, Place place) {
        try{
            if (placeId != null && place != null){
                Place oldPlace = placeRepository.findById(placeId).orElseThrow(() -> new RuntimeException("cant find place"));
                place.setId(oldPlace.getId());
                Place result = placeRepository.save(place);
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
