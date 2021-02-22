package com.crmiguez.aixinainventory.repository;

import com.crmiguez.aixinainventory.entities.ItemSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("itemSetRepository")
public interface ItemSetRepository extends CrudRepository<ItemSet, Long> {
    ItemSet findByItemSetId(Long itemSetId);
}
