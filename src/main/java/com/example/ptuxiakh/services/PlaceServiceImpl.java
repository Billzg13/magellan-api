package com.example.ptuxiakh.services;

import com.example.ptuxiakh.model.PlacePackage.Place;
import com.example.ptuxiakh.model.PlacePackage.PlaceMapper;
import com.example.ptuxiakh.model.PlacePackage.PlaceProjection;
import com.example.ptuxiakh.repository.PlaceRepository;
import com.example.ptuxiakh.repository.PlaceRepositoryV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public List<PlaceProjection> getAllPlacesByTypes(List<String> types, Integer pageSize, Integer pageNo) {
        // This is redudant but it's good practice when it comes to testing the actual function
        if (pageNo == null || pageNo == null) {
            throw new NullPointerException("pageSize or pageNo is null! ");
        }
        Pageable firstPageWithTwoElements = PageRequest.of(pageNo, pageSize);
        return placeMapper.toListPlaceProjection(
                placeRepositoryV2.findAllByTypes(
                        types,
                        firstPageWithTwoElements)
        );
    }

    @Override
    public List<PlaceProjection> getAllPlaces(Integer pageSize, Integer pageNo) {
        if (pageNo == null || pageNo == null) {
            throw new NullPointerException("pageSize or pageNo is null! ");
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return placeMapper.toListPlaceProjection(placeRepositoryV2.findAll(
                pageable).get().collect(Collectors.toList())
        );
    }

    @Override
    public Place getSinglePlace(Long placeId) {
        if (placeId == null)
            throw new NullPointerException("placeId is null");
        return placeRepository.findById(placeId).orElseThrow(() -> new NullPointerException("cant find place"));
    }


}
