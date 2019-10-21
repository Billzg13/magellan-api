package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.general.GoOutPlace;
import com.example.ptuxiakh.repository.GoOutPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoOutServiceImpl implements GoOutPlaceService{

    @Autowired
    GoOutPlaceRepository goOutPlaceRepository;

    @Override
    public List<GoOutPlace> getAllPlaces() {
        try{
            List<GoOutPlace> result = goOutPlaceRepository.findAll();
            if (result != null)
                return result;
        }catch (Exception exc){
            exc.printStackTrace();

        }
        return null;
    }

    @Override
    public GoOutPlace getSinglePlace(String placeId) throws RuntimeException{
        if (placeId != null) {
            GoOutPlace result = goOutPlaceRepository.findById(placeId).orElseThrow(() -> new RuntimeException("cant find place!"));
            return result;
        }else {
            throw new NullPointerException("placeId is null");
        }
    }

    @Override
    public Boolean deletePlace(String placeId) {
        try{
            if (placeId != null){
                goOutPlaceRepository.deleteById(placeId);
                return true;
            }
            return false;
        }catch (Exception exc){
            exc.printStackTrace();
        }
        return false;
    }

    @Override
    public GoOutPlace createNewPlace(GoOutPlace goOutPlace) {
        try{
            if (goOutPlace != null)
                return goOutPlaceRepository.save(goOutPlace);
        }catch (Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    @Override
    public GoOutPlace updatePlace(String placeId, GoOutPlace goOutPlace) {
        try{
            if (placeId != null && goOutPlace != null){
                GoOutPlace oldPlace = goOutPlaceRepository.findById(placeId).orElseThrow(() -> new RuntimeException("cant find place"));
                goOutPlace.setId(oldPlace.getId());
                GoOutPlace result = goOutPlaceRepository.save(goOutPlace);
                return result;
            }else {
                throw new NullPointerException("no placeId or GoOutPlace provided");
            }
        }catch (Exception exc){
            exc.printStackTrace();
            return null;
        }
    }
}
