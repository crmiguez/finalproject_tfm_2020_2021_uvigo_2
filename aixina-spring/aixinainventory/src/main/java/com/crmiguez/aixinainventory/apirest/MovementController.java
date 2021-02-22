package com.crmiguez.aixinainventory.apirest;

import com.crmiguez.aixinainventory.entities.Movement;
import com.crmiguez.aixinainventory.exception.ResourceNotFoundException;
import com.crmiguez.aixinainventory.service.movement.IMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/api_aixina/v1")
@RequestMapping("/api_aixina/v1/movementmanage")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class MovementController {

    @Autowired
    @Qualifier("movementService")
    private IMovementService movementService;

    @PreAuthorize("hasAuthority('PERM_READ_ALL_MOVEMENTS')")
    @GetMapping("/movements")
    public List<Movement> getAllMovements() {
        return movementService.findAllMovements();
    }

    @PreAuthorize("hasAuthority('PERM_READ_ALL_DOWNS')")
    @GetMapping("/downs")
    public List<Movement> getAllDowns() {
        return movementService.findAllDowns();
    }

    @PreAuthorize("hasAuthority('PERM_READ_MOVEMENT')")
    @GetMapping("/movements/{id}")
    public ResponseEntity<Movement> getMovementById(
            @PathVariable(value = "id") Long movementId) throws ResourceNotFoundException {
        Movement movement = movementService.findMovementById(movementId)
                .orElseThrow(() -> new ResourceNotFoundException("Movement not found on :: "+ movementId));
        return ResponseEntity.ok().body(movement);
    }

    @PreAuthorize("hasAuthority('PERM_CREATE_MOVEMENT')")
    @PostMapping("/movement")
    public Movement createMovement(@Valid @RequestBody Movement movement) { return movementService.addMovement(movement); }

    @PreAuthorize("hasAuthority('PERM_UPDATE_MOVEMENT')")
    @PutMapping("/movements/{id}")
    public ResponseEntity<Movement> updateMovement(
            @PathVariable(value = "id") Long movementId,
            @Valid @RequestBody Movement movementDetails) throws ResourceNotFoundException {
        Movement movement = movementService.findMovementById(movementId)
                .orElseThrow(() -> new ResourceNotFoundException("Movement not found on :: "+ movementId));

        final Movement updatedMovement = movementService.updateMovement(movementDetails, movement);
        if  (updatedMovement == null){
            return new ResponseEntity<Movement>(HttpStatus.EXPECTATION_FAILED);
        } else {
            return ResponseEntity.ok(updatedMovement);
        }
    }

    @PreAuthorize("hasAuthority('PERM_DELETE_MOVEMENT')")
    @DeleteMapping("/movement/{id}")
    public Map<String, Boolean> deleteMovement(
            @PathVariable(value = "id") Long movementId) throws Exception {
        Movement Movement = movementService.findMovementById(movementId)
                .orElseThrow(() -> new ResourceNotFoundException("Movement not found on :: "+ movementId));

        movementService.deleteMovement(Movement);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}

