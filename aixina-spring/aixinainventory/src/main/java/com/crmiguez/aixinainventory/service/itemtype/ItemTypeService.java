package com.crmiguez.aixinainventory.service.itemtype;

import com.crmiguez.aixinainventory.entities.ItemType;
import com.crmiguez.aixinainventory.repository.ItemTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("itemTypeService")
public class ItemTypeService implements IItemTypeService {

    @Autowired
    @Qualifier("itemTypeRepository")
    private ItemTypeRepository itemTypeRepository;

    @Override
    public List<ItemType> findAllItemTypes() {
        return (List<ItemType>) itemTypeRepository.findAll();
    }

    @Override
    public Optional<ItemType> findItemTypeById(String ItemTypeId){
        return itemTypeRepository.findById(ItemTypeId);
    }

    @Override
    public ItemType addItemType(ItemType ItemType){
        return itemTypeRepository.save(ItemType);
    }

    @Override
    public void deleteItemType (ItemType ItemType){
        itemTypeRepository.delete(ItemType);
    }

    @Override
    public ItemType updateItemType(ItemType itemTypeDetails, ItemType itemType){

        itemType.setItemTypeName(itemTypeDetails.getItemTypeName());

        return itemTypeRepository.save(itemType);
    }

}

