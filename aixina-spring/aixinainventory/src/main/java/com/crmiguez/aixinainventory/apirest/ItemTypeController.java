package com.crmiguez.aixinainventory.apirest;

import com.crmiguez.aixinainventory.entities.ItemType;
import com.crmiguez.aixinainventory.exception.ResourceNotFoundException;
import com.crmiguez.aixinainventory.service.itemtype.IItemTypeService;
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
@RequestMapping("/api_aixina/v1/itemtypemanage")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ItemTypeController {

    @Autowired
    @Qualifier("itemTypeService")
    private IItemTypeService itemTypeService;

    @PreAuthorize("hasAuthority('PERM_READ_ALL_ITEM_TYPES')")
    @GetMapping("/itemtypes")
    public List<ItemType> getAllItemTypes() {
        return itemTypeService.findAllItemTypes();
    }

    @PreAuthorize("hasAuthority('PERM_READ_ITEM_TYPE')")
    @GetMapping("/itemtypes/{id}")
    public ResponseEntity<ItemType> getItemTypeById(
            @PathVariable(value = "id") String itemTypeId) throws ResourceNotFoundException {
        ItemType itemType = itemTypeService.findItemTypeById(itemTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("ItemType not found on :: "+ itemTypeId));
        return ResponseEntity.ok().body(itemType);
    }

    @PreAuthorize("hasAuthority('PERM_CREATE_ITEM_TYPE')")
    @PostMapping("/itemtype")
    public ItemType createItemType(@Valid @RequestBody ItemType itemType) { return itemTypeService.addItemType(itemType); }

    @PreAuthorize("hasAuthority('PERM_UPDATE_ITEM_TYPE')")
    @PutMapping("/itemtypes/{id}")
    public ResponseEntity<ItemType> updateItemType(
            @PathVariable(value = "id") String itemTypeId,
            @Valid @RequestBody ItemType itemTypeDetails) throws ResourceNotFoundException {
        ItemType itemType = itemTypeService.findItemTypeById(itemTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("ItemType not found on :: "+ itemTypeId));

        final ItemType updatedItemType = itemTypeService.updateItemType(itemTypeDetails, itemType);
        if  (updatedItemType == null){
            return new ResponseEntity<ItemType>(HttpStatus.EXPECTATION_FAILED);
        } else {
            return ResponseEntity.ok(updatedItemType);
        }
    }

    @PreAuthorize("hasAuthority('PERM_DELETE_ITEM_TYPE')")
    @DeleteMapping("/itemtype/{id}")
    public Map<String, Boolean> deleteItemType(
            @PathVariable(value = "id") String itemTypeId) throws Exception {
        ItemType ItemType = itemTypeService.findItemTypeById(itemTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("ItemType not found on :: "+ itemTypeId));

        itemTypeService.deleteItemType(ItemType);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
