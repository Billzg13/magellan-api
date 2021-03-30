package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.PlacePackage.Place;
import com.example.ptuxiakh.model.PlacePackage.PlaceMapper;
import com.example.ptuxiakh.model.PlacePackage.PlaceProjection;
import com.example.ptuxiakh.repository.PlaceRepository;
import com.example.ptuxiakh.repository.PlaceRepositoryV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    PlaceRepositoryV2 placeRepositoryV2;

    PlaceMapper placeMapper = new PlaceMapper();

    @Override
    public List<PlaceProjection> getAllPlacesByTypes(List<String> types, int pageSize, int pageNo) {
        Pageable firstPageWithTwoElements = PageRequest.of(pageNo, pageSize);

        return placeMapper.toListPlaceProjection(placeRepositoryV2.findAllByTypes(types, firstPageWithTwoElements));
    }

    @Override
    public List<PlaceProjection> getAllPlaces(int pageSize, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Place> places = placeRepositoryV2.findAll(pageable);
        return placeMapper.toListPlaceProjection(places.get().collect(Collectors.toList()));
    }

    @Override
    public Place getSinglePlace(Long placeId) {
        if (placeId == null)
            throw new NullPointerException("placeId is null");
        return placeRepository.findById(placeId).orElseThrow(()-> new NullPointerException("cant find place"));
    }


}
