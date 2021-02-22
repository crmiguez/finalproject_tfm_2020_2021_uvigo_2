package com.crmiguez.aixinainventory.service.itemset;

import com.crmiguez.aixinainventory.entities.ItemSet;
import com.crmiguez.aixinainventory.repository.ItemSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("itemSetService")
public class ItemSetService implements IItemSetService {

    @Autowired
    @Qualifier("itemSetRepository")
    private ItemSetRepository itemSetRepository;

    @Override
    public List<ItemSet> findAllItemSets() {
        return (List<ItemSet>) itemSetRepository.findAll();
    }

    @Override
    public Optional<ItemSet> findItemSetById(Long itemSetId){
        return itemSetRepository.findById(itemSetId);
    }

    @Override
    public ItemSet addItemSet(ItemSet itemSet){
        return itemSetRepository.save(itemSet);
    }

    @Override
    public void deleteItemSet (ItemSet itemSet){
        itemSetRepository.delete(itemSet);
    }

    @Override
    public ItemSet updateItemSet(ItemSet itemSetDetails, ItemSet itemSet){

        itemSet.setItemSetName(itemSetDetails.getItemSetName());
        itemSet.setItemSetStickerNumber(itemSetDetails.getItemSetStickerNumber());
        itemSet.setItemSetRegisterDate(itemSetDetails.getItemSetRegisterDate());
        itemSet.setItemSetShutDate(itemSetDetails.getItemSetShutDate());

        return itemSetRepository.save(itemSet);
    }
}
