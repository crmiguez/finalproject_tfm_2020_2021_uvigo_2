package com.crmiguez.aixinainventory.service.inventory;

import com.crmiguez.aixinainventory.entities.Inventory;
import com.crmiguez.aixinainventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("inventoryService")
public class InventoryService implements IInventoryService {

    @Autowired
    @Qualifier("inventoryRepository")
    private InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> findAllInventorys() {
        return (List<Inventory>) inventoryRepository.findAll();
    }

    @Override
    public Optional<Inventory> findInventoryById(Long inventoryId){
        return inventoryRepository.findById(inventoryId);
    }

    @Override
    public Inventory addInventory(Inventory inventory){
        return inventoryRepository.save(inventory);
    }

    @Override
    public void deleteInventory (Inventory inventory){
        inventoryRepository.delete(inventory);
    }

    @Override
    public Inventory updateInventory(Inventory inventoryDetails, Inventory inventory){

        inventory.setInventoryControlNumber(inventoryDetails.getInventoryControlNumber());
        inventory.setInvoice(inventoryDetails.getInvoice());
        inventory.setEmployee(inventoryDetails.getEmployee());
        inventory.setInventoryDate(inventoryDetails.getInventoryDate());
        inventory.setInventoryYear(inventoryDetails.getInventoryYear());
        inventory.setInventoryUnits(inventoryDetails.getInventoryUnits());
        inventory.setInventoryUnitCost(inventoryDetails.getInventoryUnitCost());
        inventory.setInventoryTotalValue(inventoryDetails.getInventoryTotalValue());
        inventory.setLocation(inventoryDetails.getLocation());

        return inventoryRepository.save(inventory);
    }

}
