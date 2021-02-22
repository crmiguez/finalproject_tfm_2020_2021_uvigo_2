package com.crmiguez.aixinainventory.apirest;

import com.crmiguez.aixinainventory.entities.Line;
import com.crmiguez.aixinainventory.exception.ResourceNotFoundException;
import com.crmiguez.aixinainventory.service.line.ILineService;
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
@RequestMapping("/api_aixina/v1/linemanage")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class LineController {

    @Autowired
    @Qualifier("lineService")
    private ILineService lineService;

    @PreAuthorize("hasAuthority('PERM_READ_ALL_LINES')")
    @GetMapping("/lines")
    public List<Line> getAllLines() {
        return lineService.findAllLines();
    }

    @PreAuthorize("hasAuthority('PERM_READ_LINE')")
    @GetMapping("/lines/{id}")
    public ResponseEntity<Line> getLineById(
            @PathVariable(value = "id") Long lineId) throws ResourceNotFoundException {
        Line line = lineService.findLineById(lineId)
                .orElseThrow(() -> new ResourceNotFoundException("Line not found on :: "+ lineId));
        return ResponseEntity.ok().body(line);
    }

    @PreAuthorize("hasAuthority('PERM_CREATE_LINE')")
    @PostMapping("/line")
    public Line createLine(@Valid @RequestBody Line line) { return lineService.addLine(line); }

    @PreAuthorize("hasAuthority('PERM_UPDATE_LINE')")
    @PutMapping("/lines/{id}")
    public ResponseEntity<Line> updateLine(
            @PathVariable(value = "id") Long lineId,
            @Valid @RequestBody Line lineDetails) throws ResourceNotFoundException {
        Line line = lineService.findLineById(lineId)
                .orElseThrow(() -> new ResourceNotFoundException("Line not found on :: "+ lineId));

        final Line updatedLine = lineService.updateLine(lineDetails, line);
        if  (updatedLine == null){
            return new ResponseEntity<Line>(HttpStatus.EXPECTATION_FAILED);
        } else {
            return ResponseEntity.ok(updatedLine);
        }
    }

    @PreAuthorize("hasAuthority('PERM_DELETE_LINE')")
    @DeleteMapping("/line/{id}")
    public Map<String, Boolean> deleteLine(
            @PathVariable(value = "id") Long lineId) throws Exception {
        Line line = lineService.findLineById(lineId)
                .orElseThrow(() -> new ResourceNotFoundException("Line not found on :: "+ lineId));

        lineService.deleteLine(line);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
