package com.crmiguez.aixinainventory.repository;

import com.crmiguez.aixinainventory.entities.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("locationRepository")
public interface LocationRepository extends CrudRepository<Location, Long> {
    Location findByLocationId(Long locationId);
}
