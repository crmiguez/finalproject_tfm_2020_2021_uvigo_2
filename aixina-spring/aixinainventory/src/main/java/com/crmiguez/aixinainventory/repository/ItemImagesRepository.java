package com.crmiguez.aixinainventory.repository;

import com.crmiguez.aixinainventory.entities.ItemImages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("itemImagesRepository")
public interface ItemImagesRepository extends CrudRepository<ItemImages, Long> {
    ItemImages findByItemImagesId(Long itemImagesId);
}
