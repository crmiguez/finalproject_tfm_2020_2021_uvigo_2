package com.crmiguez.aixinainventory.repository;

import com.crmiguez.aixinainventory.entities.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("inventoryRepository")
public interface InventoryRepository extends CrudRepository<Inventory, Long> {
    Inventory findByInventoryId(Long inventoryId);
}
