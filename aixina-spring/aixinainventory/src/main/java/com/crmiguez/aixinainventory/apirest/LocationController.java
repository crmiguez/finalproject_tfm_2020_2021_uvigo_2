package com.crmiguez.aixinainventory.apirest;

import com.crmiguez.aixinainventory.entities.Location;
import com.crmiguez.aixinainventory.exception.ResourceNotFoundException;
import com.crmiguez.aixinainventory.service.location.ILocationService;
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
@RequestMapping("/api_aixina/v1/locationmanage")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class LocationController {

    @Autowired
    @Qualifier("locationService")
    private ILocationService locationService;

    @PreAuthorize("hasAuthority('PERM_READ_ALL_LOCATIONS')")
    @GetMapping("/locations")
    public List<Location> getAllLocations() {
        return locationService.findAllLocations();
    }

    @PreAuthorize("hasAuthority('PERM_READ_LOCATION')")
    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> getLocationById(
            @PathVariable(value = "id") Long locationId) throws ResourceNotFoundException {
        Location location = locationService.findLocationById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found on :: "+ locationId));
        return ResponseEntity.ok().body(location);
    }

    @PreAuthorize("hasAuthority('PERM_CREATE_LOCATION')")
    @PostMapping("/location")
    public Location createLocation(@Valid @RequestBody Location location) { return locationService.addLocation(location); }

    @PreAuthorize("hasAuthority('PERM_UPDATE_LOCATION')")
    @PutMapping("/locations/{id}")
    public ResponseEntity<Location> updateLocation(
            @PathVariable(value = "id") Long locationId,
            @Valid @RequestBody Location locationDetails) throws ResourceNotFoundException {
        Location location = locationService.findLocationById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found on :: "+ locationId));

        final Location updatedLocation = locationService.updateLocation(locationDetails, location);
        if  (updatedLocation == null){
            return new ResponseEntity<Location>(HttpStatus.EXPECTATION_FAILED);
        } else {
            return ResponseEntity.ok(updatedLocation);
        }
    }

    @PreAuthorize("hasAuthority('PERM_DELETE_LOCATION')")
    @DeleteMapping("/location/{id}")
    public Map<String, Boolean> deleteLocation(
            @PathVariable(value = "id") Long locationId) throws Exception {
        Location Location = locationService.findLocationById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found on :: "+ locationId));

        locationService.deleteLocation(Location);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
