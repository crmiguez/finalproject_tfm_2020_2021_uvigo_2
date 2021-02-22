package com.crmiguez.aixinainventory.repository;

import com.crmiguez.aixinainventory.entities.ItemType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("itemTypeRepository")
public interface ItemTypeRepository extends CrudRepository<ItemType, String> {
    ItemType findByItemTypeId(String itemTypeId);
}
