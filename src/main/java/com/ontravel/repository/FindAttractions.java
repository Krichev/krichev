package com.ontravel.repository;

import com.ontravel.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FindAttractions extends CrudRepository<City, String> {



}


