package com.crmiguez.aixinainventory.service.item;

import com.crmiguez.aixinainventory.entities.Item;

import java.util.List;
import java.util.Optional;

public interface IItemService {
    public List<Item> findAllItems();
    public Optional<Item> findItemById(Long itemId);
    public Item addItem(Item item);
    public Item updateItem(Item itemDetails, Item item);
    public void deleteItem (Item item);

}
