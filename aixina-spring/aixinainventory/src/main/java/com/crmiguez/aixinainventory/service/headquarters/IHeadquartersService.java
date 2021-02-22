package com.crmiguez.aixinainventory.service.headquarters;

import com.crmiguez.aixinainventory.entities.Headquarters;

import java.util.List;
import java.util.Optional;

public interface IHeadquartersService {
    public List<Headquarters> findAllHeadquarterss();
    public Optional<Headquarters> findHeadquartersById(String HeadquartersId);
    public Headquarters addHeadquarters(Headquarters Headquarters);
    public Headquarters updateHeadquarters(Headquarters HeadquartersDetails, Headquarters Headquarters);
    public void deleteHeadquarters (Headquarters Headquarters);

}
