package com.crmiguez.aixinainventory.service.inventory;

import com.crmiguez.aixinainventory.entities.Inventory;

import java.util.List;
import java.util.Optional;

public interface IInventoryService {
    public List<Inventory> findAllInventorys();
    public Optional<Inventory> findInventoryById(Long inventoryId);
    public Inventory addInventory(Inventory inventory);
    public Inventory updateInventory(Inventory inventoryDetails, Inventory inventory);
    public void deleteInventory (Inventory inventory);

}
