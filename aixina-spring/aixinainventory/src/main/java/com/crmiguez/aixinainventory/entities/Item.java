package com.crmiguez.aixinainventory.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "item")
@Table
public class Item implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "itemId")
    private Long itemId;

    @Column(name = "itemName")
    private String itemName;

    @Column(name = "itemStickerNumber")
    private String itemStickerNumber;

    @Column(name = "itemRegisterDate")
    private String itemRegisterDate;

    @Column(name = "itemShutDate")
    private String itemShutDate;

    @JoinColumn(name = "itemSetId", referencedColumnName = "itemSetId")
    @ManyToOne
    private ItemSet itemSet;

    @JoinColumn(name = "itemTypeId", referencedColumnName = "itemTypeId", nullable = false)
    @ManyToOne
    private ItemType itemType;

    @JoinColumn(name = "brandId", referencedColumnName = "brandId", nullable = false)
    @ManyToOne
    private Brand brand;

    public Item() {
    }

    public Item(Long itemId, String itemName, String itemStickerNumber, String itemRegisterDate, String itemShutDate, ItemSet itemSet, ItemType itemType, Brand brand) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemStickerNumber = itemStickerNumber;
        this.itemRegisterDate = itemRegisterDate;
        this.itemShutDate = itemShutDate;
        this.itemSet = itemSet;
        this.itemType = itemType;
        this.brand = brand;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemStickerNumber() {
        return itemStickerNumber;
    }

    public void setItemStickerNumber(String itemStickerNumber) {
        this.itemStickerNumber = itemStickerNumber;
    }

    public String getItemRegisterDate() {
        return itemRegisterDate;
    }

    public void setItemRegisterDate(String itemRegisterDate) {
        this.itemRegisterDate = itemRegisterDate;
    }

    public String getItemShutDate() {
        return itemShutDate;
    }

    public void setItemShutDate(String itemShutDate) {
        this.itemShutDate = itemShutDate;
    }

    public ItemSet getItemSet() {
        return itemSet;
    }

    public void setItemSet(ItemSet itemSet) {
        this.itemSet = itemSet;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
