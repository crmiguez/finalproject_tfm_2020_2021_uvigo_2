package com.crmiguez.aixinainventory.service.headquarters;

import com.crmiguez.aixinainventory.entities.Headquarters;
import com.crmiguez.aixinainventory.repository.HeadquartersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("headquartersService")
public class HeadquartersService implements IHeadquartersService {

    @Autowired
    @Qualifier("headquartersRepository")
    private HeadquartersRepository headquartersRepository;

    @Override
    public List<Headquarters> findAllHeadquarterss() {
        return (List<Headquarters>) headquartersRepository.findAll();
    }

    @Override
    public Optional<Headquarters> findHeadquartersById(String headquartersId){
        return headquartersRepository.findById(headquartersId);
    }

    @Override
    public Headquarters addHeadquarters(Headquarters headquarters){
        return headquartersRepository.save(headquarters);
    }

    @Override
    public void deleteHeadquarters (Headquarters headquarters){
        headquartersRepository.delete(headquarters);
    }

    @Override
    public Headquarters updateHeadquarters(Headquarters headquartersDetails, Headquarters headquarters){

        headquarters.setHeadquartersName(headquartersDetails.getHeadquartersName());
        headquarters.setHeadquartersAddress(headquartersDetails.getHeadquartersAddress());
        headquarters.setHeadquartersLocation(headquartersDetails.getHeadquartersLocation());
        headquarters.setHeadquartersWebSite(headquartersDetails.getHeadquartersWebSite());

        return headquartersRepository.save(headquarters);
    }

}
