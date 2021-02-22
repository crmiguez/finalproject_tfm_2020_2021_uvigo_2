package com.crmiguez.aixinainventory.service.movetype;

import com.crmiguez.aixinainventory.entities.MoveType;

import java.util.List;
import java.util.Optional;

public interface IMoveTypeService {
    public List<MoveType> findAllMoveTypes();
    public Optional<MoveType> findMoveTypeById(String MoveTypeId);
    public MoveType addMoveType(MoveType MoveType);
    public MoveType updateMoveType(MoveType MoveTypeDetails, MoveType MoveType);
    public void deleteMoveType (MoveType MoveType);

}
