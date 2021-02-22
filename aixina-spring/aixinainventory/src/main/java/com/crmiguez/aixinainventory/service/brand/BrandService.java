package com.crmiguez.aixinainventory.service.brand;

import com.crmiguez.aixinainventory.entities.Brand;
import com.crmiguez.aixinainventory.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("brandService")
public class BrandService implements IBrandService {

    @Autowired
    @Qualifier("brandRepository")
    private BrandRepository brandRepository;

    @Override
    public List<Brand> findAllBrands() {
        return (List<Brand>) brandRepository.findAll();
    }

    @Override
    public Optional<Brand> findBrandById(String brandId){
        return brandRepository.findById(brandId);
    }

    @Override
    public Brand addBrand(Brand brand){
        return brandRepository.save(brand);
    }

    @Override
    public void deleteBrand (Brand Brand){
        brandRepository.delete(Brand);
    }

    @Override
    public Brand updateBrand(Brand brandDetails, Brand brand){

        brand.setBrandName(brandDetails.getBrandName());
        brand.setBrandAddress(brandDetails.getBrandAddress());
        brand.setBrandPhone(brandDetails.getBrandPhone());
        brand.setBrandEmail(brandDetails.getBrandEmail());
        brand.setBrandWebSite(brandDetails.getBrandWebSite());


        return brandRepository.save(brand);
    }

}
