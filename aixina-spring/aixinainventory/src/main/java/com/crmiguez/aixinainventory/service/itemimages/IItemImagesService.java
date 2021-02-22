package com.crmiguez.aixinainventory.service.itemimages;

import com.crmiguez.aixinainventory.entities.ItemImages;

import java.util.List;
import java.util.Optional;

public interface IItemImagesService {
    public List<ItemImages> findAllItemImagess();
    public Optional<ItemImages> findItemImagesById(Long itemImagesId);
    public ItemImages addItemImages(ItemImages itemImages);
    public ItemImages updateItemImages(ItemImages itemImagesDetails, ItemImages itemImages);
    public void deleteItemImages (ItemImages ItemImages);

}
