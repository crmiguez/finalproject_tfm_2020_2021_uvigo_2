package com.crmiguez.aixinainventory.service.movement;

import com.crmiguez.aixinainventory.entities.Movement;

import java.util.List;
import java.util.Optional;

public interface IMovementService {
    public List<Movement> findAllMovements();
    public Optional<Movement> findMovementById(Long movementId);
    public List<Movement> findAllDowns();
    public Movement addMovement(Movement Movement);
    public Movement updateMovement(Movement movementDetails, Movement movement);
    public void deleteMovement (Movement movement);

}
