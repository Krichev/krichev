package com.ontravel.controller;

import com.ontravel.model.City;
import com.ontravel.repository.AddCityOrAttractions;
import com.ontravel.service.OntravelBot;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SendMessageController {
    OntravelBot ontravelBot;
    AddCityOrAttractions addCityOrAttractions;

    @GetMapping("/add-city/{cityName}")
    @ResponseStatus(HttpStatus.OK)
    public void addCity(@PathVariable String cityName) {
        City city = getCity(cityName);
        addCityOrAttractions.save(city);

    }

    @GetMapping("/delete-city/{cityName}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCity(@PathVariable String cityName) {
        City city = getCity(cityName);
        addCityOrAttractions.delete(city);
    }

    @GetMapping("/add-attraction/{attraction}/{cityName}")
    @ResponseStatus(HttpStatus.OK)
    public void addAttractions(@PathVariable("attraction") String attraction,@PathVariable("cityName") String cityName ) {
        try {
            City city = addCityOrAttractions.findById(cityName).orElseThrow(new NosuchCityException());
            String attractions = city.getAttractions();
            if(attractions==null)attractions="";
            attractions.replaceAll("-",", " );
            attractions+=" "+attraction;
            city.setAttractions(attractions);
            addCityOrAttractions.save(city);
//            attraction.replace(attraction, "");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @GetMapping("/delete-attraction/{attraction}/{cityName}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAttractions(@PathVariable("attraction") String attraction,@PathVariable("cityName") String cityName ) {
        try {
            City city = addCityOrAttractions.findById(cityName).orElseThrow(new NosuchCityException());
            String attractions = city.getAttractions();
            if(attractions==null)attractions="";
            attraction.replace(attraction, "");
            city.setAttractions(attractions);
            addCityOrAttractions.save(city);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private synchronized City getCity( String cityName) {
        City city = new City();
        city.setCity(cityName);
        return city;
    }

}
