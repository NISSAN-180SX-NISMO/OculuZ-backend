package com.zuluco.oculuz.model.services;

import com.zuluco.oculuz.model.entities.Country;
import com.zuluco.oculuz.model.repos.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class CountryService {
    @Autowired
    private CountryRepo countryRepo;

    @Transactional
    public void saveCountry(String name, String code) {
        Country country = new Country();
        country.setName(name);
        countryRepo.save(country);
    }
}
