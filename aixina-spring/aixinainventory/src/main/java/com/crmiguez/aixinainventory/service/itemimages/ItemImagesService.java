package com.crmiguez.aixinainventory.service.itemimages;

import com.crmiguez.aixinainventory.entities.ItemImages;
import com.crmiguez.aixinainventory.repository.ItemImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("itemImagesService")
public class ItemImagesService implements IItemImagesService {

    @Autowired
    @Qualifier("itemImagesRepository")
    private ItemImagesRepository itemImagesRepository;

    @Override
    public List<ItemImages> findAllItemImagess() {
        return (List<ItemImages>) itemImagesRepository.findAll();
    }

    @Override
    public Optional<ItemImages> findItemImagesById(Long itemImagesId){
        return itemImagesRepository.findById(itemImagesId);
    }

    @Override
    public ItemImages addItemImages(ItemImages itemImages){
        return itemImagesRepository.save(itemImages);
    }

    @Override
    public void deleteItemImages (ItemImages itemImages){
        itemImagesRepository.delete(itemImages);
    }

    @Override
    public ItemImages updateItemImages(ItemImages itemImagesDetails, ItemImages itemImages){

        itemImages.setItemImagesName(itemImagesDetails.getItemImagesName());
        itemImages.setItemImagesFile(itemImagesDetails.getItemImagesFile());
        itemImages.setItem(itemImagesDetails.getItem());
        itemImages.setItemSet(itemImagesDetails.getItemSet());
        itemImages.setUploadDate(itemImagesDetails.getUploadDate());

        return itemImagesRepository.save(itemImages);
    }

}
