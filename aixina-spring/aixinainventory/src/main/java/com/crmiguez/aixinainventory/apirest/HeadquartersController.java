package com.crmiguez.aixinainventory.apirest;

import com.crmiguez.aixinainventory.entities.Headquarters;
import com.crmiguez.aixinainventory.exception.ResourceNotFoundException;
import com.crmiguez.aixinainventory.service.headquarters.IHeadquartersService;
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
@RequestMapping("/api_aixina/v1/headquartersmanage")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class HeadquartersController {

    @Autowired
    @Qualifier("headquartersService")
    private IHeadquartersService headquartersService;

    @PreAuthorize("hasAuthority('PERM_READ_ALL_HEADQUARTERS')")
    @GetMapping("/headquarters")
    public List<Headquarters> getAllHeadquarterss() {
        return headquartersService.findAllHeadquarterss();
    }

    @PreAuthorize("hasAuthority('PERM_READ_HEADQUARTER')")
    @GetMapping("/headquarters/{id}")
    public ResponseEntity<Headquarters> getHeadquartersById(
            @PathVariable(value = "id") String headquartersId) throws ResourceNotFoundException {
        Headquarters headquarters = headquartersService.findHeadquartersById(headquartersId)
                .orElseThrow(() -> new ResourceNotFoundException("Headquarters not found on :: "+ headquartersId));
        return ResponseEntity.ok().body(headquarters);
    }

    @PreAuthorize("hasAuthority('PERM_CREATE_HEADQUARTER')")
    @PostMapping("/headquarters")
    public Headquarters createHeadquarters(@Valid @RequestBody Headquarters headquarters) { return headquartersService.addHeadquarters(headquarters); }

    @PreAuthorize("hasAuthority('PERM_UPDATE_HEADQUARTERS')")
    @PutMapping("/headquarters/{id}")
    public ResponseEntity<Headquarters> updateHeadquarters(
            @PathVariable(value = "id") String headquartersId,
            @Valid @RequestBody Headquarters headquartersDetails) throws ResourceNotFoundException {
        Headquarters headquarters = headquartersService.findHeadquartersById(headquartersId)
                .orElseThrow(() -> new ResourceNotFoundException("Headquarters not found on :: "+ headquartersId));

        final Headquarters updatedHeadquarters = headquartersService.updateHeadquarters(headquartersDetails, headquarters);
        if  (updatedHeadquarters == null){
            return new ResponseEntity<Headquarters>(HttpStatus.EXPECTATION_FAILED);
        } else {
            return ResponseEntity.ok(updatedHeadquarters);
        }
    }

    @PreAuthorize("hasAuthority('PERM_DELETE_HEADQUARTERS')")
    @DeleteMapping("/headquarters/{id}")
    public Map<String, Boolean> deleteHeadquarters(
            @PathVariable(value = "id") String headquartersId) throws Exception {
        Headquarters Headquarters = headquartersService.findHeadquartersById(headquartersId)
                .orElseThrow(() -> new ResourceNotFoundException("Headquarters not found on :: "+ headquartersId));

        headquartersService.deleteHeadquarters(Headquarters);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
