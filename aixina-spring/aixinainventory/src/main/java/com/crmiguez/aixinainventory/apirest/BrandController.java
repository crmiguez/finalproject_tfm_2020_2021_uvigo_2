package com.crmiguez.aixinainventory.apirest;

import com.crmiguez.aixinainventory.entities.Brand;
import com.crmiguez.aixinainventory.exception.ResourceNotFoundException;
import com.crmiguez.aixinainventory.service.brand.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/api_aixina/v1")
@RequestMapping("/api_aixina/v1/brandmanage")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class BrandController {

    @Autowired
    @Qualifier("brandService")
    private IBrandService brandService;

    @PreAuthorize("hasAuthority('PERM_READ_ALL_BRANDS')")
    @GetMapping("/brands")
    public List<Brand> getAllBrands() {
        return brandService.findAllBrands();
    }

    @PreAuthorize("hasAuthority('PERM_READ_BRAND')")
    @GetMapping("/brands/{id}")
    public ResponseEntity<Brand> getBrandById(
            @PathVariable(value = "id") String brandId) throws ResourceNotFoundException {
        Brand brand = brandService.findBrandById(brandId)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found on :: "+ brandId));
        return ResponseEntity.ok().body(brand);
    }

    @PreAuthorize("hasAuthority('PERM_CREATE_BRAND')")
    @PostMapping("/brand")
    public Brand createBrand(@Valid @RequestBody Brand brand) { return brandService.addBrand(brand); }

    @PreAuthorize("hasAuthority('PERM_UPDATE_BRAND')")
    @PutMapping("/brands/{id}")
    public ResponseEntity<Brand> updateBrand(
            @PathVariable(value = "id") String brandId,
            @Valid @RequestBody Brand brandDetails) throws ResourceNotFoundException {
        Brand brand = brandService.findBrandById(brandId)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found on :: "+ brandId));

        final Brand updatedBrand = brandService.updateBrand(brandDetails, brand);
        if  (updatedBrand == null){
            return new ResponseEntity<Brand>(HttpStatus.EXPECTATION_FAILED);
        } else {
            return ResponseEntity.ok(updatedBrand);
        }
    }

    @PreAuthorize("hasAuthority('PERM_DELETE_BRAND')")
    @DeleteMapping("/brand/{id}")
    public Map<String, Boolean> deleteBrand(
            @PathVariable(value = "id") String brandId) throws Exception {
        Brand Brand = brandService.findBrandById(brandId)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found on :: "+ brandId));

        brandService.deleteBrand(Brand);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
