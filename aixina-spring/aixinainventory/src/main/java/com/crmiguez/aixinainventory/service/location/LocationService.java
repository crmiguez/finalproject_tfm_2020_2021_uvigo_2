package com.crmiguez.aixinainventory.service.location;

import com.crmiguez.aixinainventory.entities.Location;
import com.crmiguez.aixinainventory.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("locationService")
public class LocationService implements ILocationService {

    @Autowired
    @Qualifier("locationRepository")
    private LocationRepository locationRepository;

    @Override
    public List<Location> findAllLocations() {
        return (List<Location>) locationRepository.findAll();
    }

    @Override
    public Optional<Location> findLocationById(Long locationId){
        return locationRepository.findById(locationId);
    }

    @Override
    public Location addLocation(Location location){
        return locationRepository.save(location);
    }

    @Override
    public void deleteLocation (Location location){
        locationRepository.delete(location);
    }

    @Override
    public Location updateLocation(Location LocationDetails, Location Location){

        Location.setDepartment(LocationDetails.getDepartment());
        Location.setLocationAbbreviation(LocationDetails.getLocationAbbreviation());
        Location.setLocationDescription(LocationDetails.getLocationDescription());

        return locationRepository.save(Location);
    }

}
