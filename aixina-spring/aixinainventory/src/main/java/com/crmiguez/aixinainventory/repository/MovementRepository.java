package com.crmiguez.aixinainventory.repository;

import com.crmiguez.aixinainventory.entities.MoveType;
import com.crmiguez.aixinainventory.entities.Movement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("movementRepository")
public interface MovementRepository extends CrudRepository<Movement, Long> {
    Movement findByMovementId(Long movementId);
    List<Movement> findAllByMoveType(MoveType moveType);
}
