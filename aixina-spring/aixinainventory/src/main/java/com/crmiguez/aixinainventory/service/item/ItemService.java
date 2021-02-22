package com.crmiguez.aixinainventory.service.item;

import com.crmiguez.aixinainventory.entities.Item;
import com.crmiguez.aixinainventory.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("itemService")
public class ItemService implements IItemService {

    @Autowired
    @Qualifier("itemRepository")
    private ItemRepository itemRepository;

    @Override
    public List<Item> findAllItems() {
        return (List<Item>) itemRepository.findAll();
    }

    @Override
    public Optional<Item> findItemById(Long itemId){
        return itemRepository.findById(itemId);
    }

    @Override
    public Item addItem(Item item){
        return itemRepository.save(item);
    }

    @Override
    public void deleteItem (Item item){
        itemRepository.delete(item);
    }

    @Override
    public Item updateItem(Item itemDetails, Item item){

        item.setBrand(itemDetails.getBrand());
        item.setItemName(itemDetails.getItemName());
        item.setItemStickerNumber(itemDetails.getItemStickerNumber());
        item.setItemType(itemDetails.getItemType());
        item.setItemRegisterDate(itemDetails.getItemRegisterDate());
        item.setItemShutDate(itemDetails.getItemShutDate());

        return itemRepository.save(item);
    }

}
