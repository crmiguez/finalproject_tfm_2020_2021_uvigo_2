package com.crmiguez.aixinainventory.service.movement;

import com.crmiguez.aixinainventory.entities.MoveType;
import com.crmiguez.aixinainventory.entities.Movement;
import com.crmiguez.aixinainventory.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("movementService")
public class MovementService implements IMovementService {

    @Autowired
    @Qualifier("movementRepository")
    private MovementRepository movementRepository;

    @Override
    public List<Movement> findAllMovements() {
        return (List<Movement>) movementRepository.findAll();
    }

    @Override
    public Optional<Movement> findMovementById(Long movementId){
        return movementRepository.findById(movementId);
    }

    @Override
    public List<Movement> findAllDowns() {
        MoveType moveType = new MoveType("BAJA");
        return (List<Movement>) movementRepository.findAllByMoveType(moveType);
    }

    @Override
    public Movement addMovement(Movement movement){
        return movementRepository.save(movement);
    }

    @Override
    public void deleteMovement (Movement movement){
        movementRepository.delete(movement);
    }

    @Override
    public Movement updateMovement(Movement movementDetails, Movement movement){

        movement.setMovementDate(movementDetails.getMovementDate());
        movement.setMoveType(movementDetails.getMoveType());
        movement.setMovementUnits(movementDetails.getMovementUnits());
        movement.setMovementReason(movementDetails.getMovementReason());
        movement.setEmployeeSource(movementDetails.getEmployeeSource());
        movement.setEmployeeDestination(movementDetails.getEmployeeDestination());
        movement.setDepartmentSource(movementDetails.getDepartmentSource());
        movement.setLocationSource(movementDetails.getLocationSource());
        movement.setLocationDestination(movementDetails.getLocationDestination());
        movement.setItem(movementDetails.getItem());
        movement.setItemSet(movementDetails.getItemSet());
        movement.setEmployee(movementDetails.getEmployee());
        movement.setInvoice(movementDetails.getInvoice());

        return movementRepository.save(movement);
    }

}
