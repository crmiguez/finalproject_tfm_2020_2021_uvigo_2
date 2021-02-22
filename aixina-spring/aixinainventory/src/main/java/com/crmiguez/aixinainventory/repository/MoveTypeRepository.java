package com.crmiguez.aixinainventory.repository;

import com.crmiguez.aixinainventory.entities.MoveType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("moveTypeRepository")
public interface MoveTypeRepository extends CrudRepository<MoveType, String> {
    MoveType findByMoveTypeId(String moveTypeId);
}
