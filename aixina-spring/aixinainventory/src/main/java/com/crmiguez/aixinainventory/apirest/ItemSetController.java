package com.crmiguez.aixinainventory.apirest;

import com.crmiguez.aixinainventory.entities.ItemSet;
import com.crmiguez.aixinainventory.exception.ResourceNotFoundException;
import com.crmiguez.aixinainventory.service.itemset.IItemSetService;
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
@RequestMapping("/api_aixina/v1/itemsetmanage")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ItemSetController {

    @Autowired
    @Qualifier("itemSetService")
    private IItemSetService itemSetService;

    @PreAuthorize("hasAuthority('PERM_READ_ALL_ITEM_SETS')")
    @GetMapping("/itemsets")
    public List<ItemSet> getAllItemSets() {
        return itemSetService.findAllItemSets();
    }

    @PreAuthorize("hasAuthority('PERM_READ_ITEM_SET')")
    @GetMapping("/itemsets/{id}")
    public ResponseEntity<ItemSet> getItemSetById(
            @PathVariable(value = "id") Long itemSetId) throws ResourceNotFoundException {
        ItemSet itemSet = itemSetService.findItemSetById(itemSetId)
                .orElseThrow(() -> new ResourceNotFoundException("ItemSet not found on :: "+ itemSetId));
        return ResponseEntity.ok().body(itemSet);
    }

    @PreAuthorize("hasAuthority('PERM_CREATE_ITEM_SET')")
    @PostMapping("/itemset")
    public ItemSet createItemSet(@Valid @RequestBody ItemSet ItemSet) { return itemSetService.addItemSet(ItemSet); }

    @PreAuthorize("hasAuthority('PERM_UPDATE_ITEM_SET')")
    @PutMapping("/itemsets/{id}")
    public ResponseEntity<ItemSet> updateItemSet(
            @PathVariable(value = "id") Long itemSetId,
            @Valid @RequestBody ItemSet itemSetDetails) throws ResourceNotFoundException {
        ItemSet itemSet = itemSetService.findItemSetById(itemSetId)
                .orElseThrow(() -> new ResourceNotFoundException("ItemSet not found on :: "+ itemSetId));

        final ItemSet updatedItemSet = itemSetService.updateItemSet(itemSetDetails, itemSet);
        if  (updatedItemSet == null){
            return new ResponseEntity<ItemSet>(HttpStatus.EXPECTATION_FAILED);
        } else {
            return ResponseEntity.ok(updatedItemSet);
        }
    }

    @PreAuthorize("hasAuthority('PERM_DELETE_ITEM_SET')")
    @DeleteMapping("/itemset/{id}")
    public Map<String, Boolean> deleteItemSet(
            @PathVariable(value = "id") Long itemSetId) throws Exception {
        ItemSet itemSet = itemSetService.findItemSetById(itemSetId)
                .orElseThrow(() -> new ResourceNotFoundException("ItemSet not found on :: "+ itemSetId));

        itemSetService.deleteItemSet(itemSet);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
