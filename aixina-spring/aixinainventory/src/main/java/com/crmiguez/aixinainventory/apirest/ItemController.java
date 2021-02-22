package com.crmiguez.aixinainventory.apirest;

import com.crmiguez.aixinainventory.entities.Item;
import com.crmiguez.aixinainventory.exception.ResourceNotFoundException;
import com.crmiguez.aixinainventory.service.item.IItemService;
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
@RequestMapping("/api_aixina/v1/itemmanage")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ItemController {

    @Autowired
    @Qualifier("itemService")
    private IItemService itemService;

    @PreAuthorize("hasAuthority('PERM_READ_ALL_ITEMS')")
    @GetMapping("/items")
    public List<Item> getAllItems() {
        return itemService.findAllItems();
    }

    @PreAuthorize("hasAuthority('PERM_READ_ITEM')")
    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(
            @PathVariable(value = "id") Long itemId) throws ResourceNotFoundException {
        Item item = itemService.findItemById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found on :: "+ itemId));
        return ResponseEntity.ok().body(item);
    }

    @PreAuthorize("hasAuthority('PERM_CREATE_ITEM')")
    @PostMapping("/item")
    public Item createItem(@Valid @RequestBody Item item) { return itemService.addItem(item); }

    @PreAuthorize("hasAuthority('PERM_UPDATE_ITEM')")
    @PutMapping("/items/{id}")
    public ResponseEntity<Item> updateItem(
            @PathVariable(value = "id") Long itemId,
            @Valid @RequestBody Item itemDetails) throws ResourceNotFoundException {
        Item item = itemService.findItemById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found on :: "+ itemId));

        final Item updatedItem = itemService.updateItem(itemDetails, item);
        if  (updatedItem == null){
            return new ResponseEntity<Item>(HttpStatus.EXPECTATION_FAILED);
        } else {
            return ResponseEntity.ok(updatedItem);
        }
    }

    @PreAuthorize("hasAuthority('PERM_DELETE_ITEM')")
    @DeleteMapping("/item/{id}")
    public Map<String, Boolean> deleteItem(
            @PathVariable(value = "id") Long itemId) throws Exception {
        Item item = itemService.findItemById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found on :: "+ itemId));

        itemService.deleteItem(item);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
