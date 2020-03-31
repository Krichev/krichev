package com.ontravel.service;

import com.ontravel.model.City;
import com.ontravel.repository.FindAttractions;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class FindAttr {
    FindAttractions findAttractions;
    public Optional<City> find(Update update) {
       return findAttractions.findById(update.getMessage().getText());
    }

}
