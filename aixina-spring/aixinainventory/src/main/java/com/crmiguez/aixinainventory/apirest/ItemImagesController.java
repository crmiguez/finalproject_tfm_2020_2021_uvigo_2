package com.crmiguez.aixinainventory.apirest;

import com.crmiguez.aixinainventory.entities.ItemImages;
import com.crmiguez.aixinainventory.exception.ResourceNotFoundException;
import com.crmiguez.aixinainventory.service.itemimages.IItemImagesService;
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
@RequestMapping("/api_aixina/v1/itemimagesmanage")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ItemImagesController {

    @Autowired
    @Qualifier("itemImagesService")
    private IItemImagesService itemImagesService;

    @PreAuthorize("hasAuthority('PERM_READ_ALL_ITEM_IMAGES')")
    @GetMapping("/itemimages")
    public List<ItemImages> getAllItemImagess() {
        return itemImagesService.findAllItemImagess();
    }

    @PreAuthorize("hasAuthority('PERM_READ_ITEM_IMAGE')")
    @GetMapping("/itemimages/{id}")
    public ResponseEntity<ItemImages> getItemImagesById(
            @PathVariable(value = "id") Long itemImagesId) throws ResourceNotFoundException {
        ItemImages ItemImages = itemImagesService.findItemImagesById(itemImagesId)
                .orElseThrow(() -> new ResourceNotFoundException("ItemImages not found on :: "+ itemImagesId));
        return ResponseEntity.ok().body(ItemImages);
    }

    @PreAuthorize("hasAuthority('PERM_CREATE_ITEM_IMAGE')")
    @PostMapping("/itemimage")
    public ItemImages createItemImages(@Valid @RequestBody ItemImages itemImages) { return itemImagesService.addItemImages(itemImages); }

    @PreAuthorize("hasAuthority('PERM_UPDATE_ITEM_IMAGE')")
    @PutMapping("/itemimages/{id}")
    public ResponseEntity<ItemImages> updateItemImages(
            @PathVariable(value = "id") Long itemImagesId,
            @Valid @RequestBody ItemImages itemImagesDetails) throws ResourceNotFoundException {
        ItemImages itemImages = itemImagesService.findItemImagesById(itemImagesId)
                .orElseThrow(() -> new ResourceNotFoundException("ItemImages not found on :: "+ itemImagesId));

        final ItemImages updatedItemImages = itemImagesService.updateItemImages(itemImagesDetails, itemImages);
        if  (updatedItemImages == null){
            return new ResponseEntity<ItemImages>(HttpStatus.EXPECTATION_FAILED);
        } else {
            return ResponseEntity.ok(updatedItemImages);
        }
    }

    @PreAuthorize("hasAuthority('PERM_DELETE_ITEM_IMAGE')")
    @DeleteMapping("/itemimage/{id}")
    public Map<String, Boolean> deleteItemImages(
            @PathVariable(value = "id") Long itemImagesId) throws Exception {
        ItemImages itemImages = itemImagesService.findItemImagesById(itemImagesId)
                .orElseThrow(() -> new ResourceNotFoundException("ItemImages not found on :: "+ itemImagesId));

        itemImagesService.deleteItemImages(itemImages);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
