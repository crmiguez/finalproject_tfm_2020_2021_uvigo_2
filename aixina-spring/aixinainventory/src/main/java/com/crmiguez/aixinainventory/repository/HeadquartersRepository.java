package com.crmiguez.aixinainventory.repository;

import com.crmiguez.aixinainventory.entities.Headquarters;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("headquartersRepository")
public interface HeadquartersRepository extends CrudRepository<Headquarters, String> {
    Headquarters findByHeadquartersId(String headquartersId);
}
