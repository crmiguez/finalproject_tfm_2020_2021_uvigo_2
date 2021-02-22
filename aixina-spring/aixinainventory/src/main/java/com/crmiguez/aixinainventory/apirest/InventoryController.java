package com.crmiguez.aixinainventory.apirest;

import com.crmiguez.aixinainventory.entities.Inventory;
import com.crmiguez.aixinainventory.exception.ResourceNotFoundException;
import com.crmiguez.aixinainventory.service.inventory.IInventoryService;
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
@RequestMapping("/api_aixina/v1/inventorymanage")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class InventoryController {

    @Autowired
    @Qualifier("inventoryService")
    private IInventoryService inventoryService;

    @PreAuthorize("hasAuthority('PERM_READ_ALL_INVENTORIES')")
    @GetMapping("/inventorys")
    public List<Inventory> getAllInventorys() {
        return inventoryService.findAllInventorys();
    }

    @PreAuthorize("hasAuthority('PERM_READ_INVENTORY')")
    @GetMapping("/inventorys/{id}")
    public ResponseEntity<Inventory> getInventoryById(
            @PathVariable(value = "id") Long inventoryId) throws ResourceNotFoundException {
        Inventory inventory = inventoryService.findInventoryById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found on :: "+ inventoryId));
        return ResponseEntity.ok().body(inventory);
    }

    @PreAuthorize("hasAuthority('PERM_CREATE_INVENTORY')")
    @PostMapping("/inventory")
    public Inventory createInventory(@Valid @RequestBody Inventory inventory) { return inventoryService.addInventory(inventory); }

    @PreAuthorize("hasAuthority('PERM_UPDATE_INVENTORY')")
    @PutMapping("/inventorys/{id}")
    public ResponseEntity<Inventory> updateInventory(
            @PathVariable(value = "id") Long inventoryId,
            @Valid @RequestBody Inventory inventoryDetails) throws ResourceNotFoundException {
        Inventory inventory = inventoryService.findInventoryById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found on :: "+ inventoryId));

        final Inventory updatedInventory = inventoryService.updateInventory(inventoryDetails, inventory);
        if  (updatedInventory == null){
            return new ResponseEntity<Inventory>(HttpStatus.EXPECTATION_FAILED);
        } else {
            return ResponseEntity.ok(updatedInventory);
        }
    }

    @PreAuthorize("hasAuthority('PERM_DELETE_INVENTORY')")
    @DeleteMapping("/inventory/{id}")
    public Map<String, Boolean> deleteInventory(
            @PathVariable(value = "id") Long inventoryId) throws Exception {
        Inventory Inventory = inventoryService.findInventoryById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found on :: "+ inventoryId));

        inventoryService.deleteInventory(Inventory);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
