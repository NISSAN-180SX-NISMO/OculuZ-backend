package com.zuluco.oculuz.model.repos;

import com.zuluco.oculuz.model.entities.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepo extends CrudRepository<Country, Long> {

}
