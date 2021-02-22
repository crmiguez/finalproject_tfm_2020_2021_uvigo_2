package com.crmiguez.aixinainventory.service.brand;

import com.crmiguez.aixinainventory.entities.Brand;

import java.util.List;
import java.util.Optional;

public interface IBrandService {
    public List<Brand> findAllBrands();
    public Optional<Brand> findBrandById(String brandId);
    public Brand addBrand(Brand brand);
    public Brand updateBrand(Brand brandDetails, Brand brand);
    public void deleteBrand (Brand brand);

}
