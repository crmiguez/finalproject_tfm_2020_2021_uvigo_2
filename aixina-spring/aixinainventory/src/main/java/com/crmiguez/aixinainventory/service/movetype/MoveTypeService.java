package com.crmiguez.aixinainventory.service.movetype;

import com.crmiguez.aixinainventory.entities.MoveType;
import com.crmiguez.aixinainventory.repository.MoveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("moveTypeService")
public class MoveTypeService implements IMoveTypeService {

    @Autowired
    @Qualifier("moveTypeRepository")
    private MoveTypeRepository moveTypeRepository;

    @Override
    public List<MoveType> findAllMoveTypes() {
        return (List<MoveType>) moveTypeRepository.findAll();
    }

    @Override
    public Optional<MoveType> findMoveTypeById(String moveTypeId){
        return moveTypeRepository.findById(moveTypeId);
    }

    @Override
    public MoveType addMoveType(MoveType moveType){
        return moveTypeRepository.save(moveType);
    }

    @Override
    public void deleteMoveType (MoveType moveType){
        moveTypeRepository.delete(moveType);
    }

    @Override
    public MoveType updateMoveType(MoveType moveTypeDetails, MoveType moveType){
        
        moveType.setMoveTypeName(moveTypeDetails.getMoveTypeName());
        moveType.setMoveTypeDescription(moveTypeDetails.getMoveTypeDescription());

        return moveTypeRepository.save(moveType);
    }

}
