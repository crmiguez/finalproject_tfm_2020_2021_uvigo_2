package com.crmiguez.aixinainventory.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "itemimages")
@Table
public class ItemImages implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "itemImagesId")
    private Long itemImagesId;

    @Column(name = "itemImagesName")
    private String itemImagesName;

    @Column(name = "itemImagesFile")
    @Lob
    private String itemImagesFile;

    @JoinColumn(name = "itemId", referencedColumnName = "itemId")
    @ManyToOne
    private Item item;

    @JoinColumn(name = "itemSetId", referencedColumnName = "itemSetId")
    @ManyToOne
    private ItemSet itemSet;

    @Column(name = "uploadDate")
    private String  uploadDate;

    public ItemImages() {
    }

    public ItemImages(Long itemImagesId, String itemImagesName, String itemImagesFile, Item item, ItemSet itemSet, String uploadDate) {
        this.itemImagesId = itemImagesId;
        this.itemImagesName = itemImagesName;
        this.itemImagesFile = itemImagesFile;
        this.item = item;
        this.itemSet = itemSet;
        this.uploadDate = uploadDate;
    }

    public Long getItemImagesId() {
        return itemImagesId;
    }

    public void setItemImagesId(Long itemImagesId) {
        this.itemImagesId = itemImagesId;
    }

    public String getItemImagesName() {
        return itemImagesName;
    }

    public void setItemImagesName(String itemImagesName) {
        this.itemImagesName = itemImagesName;
    }

    public String getItemImagesFile() {
        return itemImagesFile;
    }

    public void setItemImagesFile(String itemImagesFile) {
        this.itemImagesFile = itemImagesFile;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ItemSet getItemSet() {
        return itemSet;
    }

    public void setItemSet(ItemSet itemSet) {
        this.itemSet = itemSet;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }
}
