package com.crmiguez.aixinainventory.repository;

import com.crmiguez.aixinainventory.entities.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("brandRepository")
public interface BrandRepository extends CrudRepository<Brand, String> {
    Brand findByBrandId(String brandId);
}
