package com.crmiguez.aixinainventory.service.location;

import com.crmiguez.aixinainventory.entities.Location;

import java.util.List;
import java.util.Optional;

public interface ILocationService {
    public List<Location> findAllLocations();
    public Optional<Location> findLocationById(Long locationId);
    public Location addLocation(Location location);
    public Location updateLocation(Location locationDetails, Location location);
    public void deleteLocation (Location location);

}
