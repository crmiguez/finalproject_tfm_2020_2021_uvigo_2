package com.crmiguez.aixinainventory.repository;

import com.crmiguez.aixinainventory.entities.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("itemRepository")
public interface ItemRepository extends CrudRepository<Item, Long> {
    Item findByItemId(Long itemId);
}
