package com.crmiguez.aixinainventory.service.itemtype;

import com.crmiguez.aixinainventory.entities.ItemType;

import java.util.List;
import java.util.Optional;

public interface IItemTypeService {
    public List<ItemType> findAllItemTypes();
    public Optional<ItemType> findItemTypeById(String itemTypeId);
    public ItemType addItemType(ItemType itemType);
    public ItemType updateItemType(ItemType itemTypeDetails, ItemType itemType);
    public void deleteItemType (ItemType itemType);

}
