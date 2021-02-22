package com.crmiguez.aixinainventory.apirest;

import com.crmiguez.aixinainventory.entities.MoveType;
import com.crmiguez.aixinainventory.exception.ResourceNotFoundException;
import com.crmiguez.aixinainventory.service.movetype.IMoveTypeService;
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
@RequestMapping("/api_aixina/v1/movetypemanage")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class MoveTypeController {

    @Autowired
    @Qualifier("moveTypeService")
    private IMoveTypeService moveTypeService;

    @PreAuthorize("hasAuthority('PERM_READ_ALL_MOVE_TYPES')")
    @GetMapping("/movetypes")
    public List<MoveType> getAllMoveTypes() {
        return moveTypeService.findAllMoveTypes();
    }

    @PreAuthorize("hasAuthority('PERM_READ_MOVE_TYPE')")
    @GetMapping("/movetypes/{id}")
    public ResponseEntity<MoveType> getMoveTypeById(
            @PathVariable(value = "id") String moveTypeId) throws ResourceNotFoundException {
        MoveType moveType = moveTypeService.findMoveTypeById(moveTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("MoveType not found on :: "+ moveTypeId));
        return ResponseEntity.ok().body(moveType);
    }

    @PreAuthorize("hasAuthority('PERM_CREATE_MOVE_TYPE')")
    @PostMapping("/movetype")
    public MoveType createMoveType(@Valid @RequestBody MoveType moveType) { return moveTypeService.addMoveType(moveType); }

    @PreAuthorize("hasAuthority('PERM_UPDATE_MOVE_TYPE')")
    @PutMapping("/movetypes/{id}")
    public ResponseEntity<MoveType> updateMoveType(
            @PathVariable(value = "id") String moveTypeId,
            @Valid @RequestBody MoveType moveTypeDetails) throws ResourceNotFoundException {
        MoveType moveType = moveTypeService.findMoveTypeById(moveTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("MoveType not found on :: "+ moveTypeId));

        final MoveType updatedMoveType = moveTypeService.updateMoveType(moveTypeDetails, moveType);
        if  (updatedMoveType == null){
            return new ResponseEntity<MoveType>(HttpStatus.EXPECTATION_FAILED);
        } else {
            return ResponseEntity.ok(updatedMoveType);
        }
    }

    @PreAuthorize("hasAuthority('PERM_DELETE_MOVE_TYPE')")
    @DeleteMapping("/movetype/{id}")
    public Map<String, Boolean> deleteMoveType(
            @PathVariable(value = "id") String moveTypeId) throws Exception {
        MoveType MoveType = moveTypeService.findMoveTypeById(moveTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("MoveType not found on :: "+ moveTypeId));

        moveTypeService.deleteMoveType(MoveType);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
