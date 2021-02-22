package com.crmiguez.aixinainventory.service.itemset;

import com.crmiguez.aixinainventory.entities.ItemSet;

import java.util.List;
import java.util.Optional;

public interface IItemSetService {
    public List<ItemSet> findAllItemSets();
    public Optional<ItemSet> findItemSetById(Long itemSetId);
    public ItemSet addItemSet(ItemSet itemSet);
    public ItemSet updateItemSet(ItemSet itemSetDetails, ItemSet itemSet);
    public void deleteItemSet (ItemSet itemSet);
}
