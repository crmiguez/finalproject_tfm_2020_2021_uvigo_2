package com.crmiguez.aixinainventory.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "itemset")
@Table
public class ItemSet implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "itemSetId")
    private Long itemSetId;

    @Column(name = "itemSetName")
    private String itemSetName;

    @Column(name = "itemSetStickerNumber")
    private String itemSetStickerNumber;

    @Column(name = "itemSetRegisterDate")
    private String itemSetRegisterDate;

    @Column(name = "itemSetShutDate")
    private String itemSetShutDate;

    @JoinColumn(name = "itemTypeId", referencedColumnName = "itemTypeId", nullable = false)
    @ManyToOne
    private ItemType itemType;

    public ItemSet() {
    }

    public ItemSet(Long itemSetId, String itemSetName, String itemSetStickerNumber, String itemSetRegisterDate, String itemSetShutDate, ItemType itemType) {
        this.itemSetId = itemSetId;
        this.itemSetName = itemSetName;
        this.itemSetStickerNumber = itemSetStickerNumber;
        this.itemSetRegisterDate = itemSetRegisterDate;
        this.itemSetShutDate = itemSetShutDate;
        this.itemType = itemType;
    }

    public Long getItemSetId() {
        return itemSetId;
    }

    public void setItemSetId(Long itemSetId) {
        this.itemSetId = itemSetId;
    }

    public String getItemSetName() {
        return itemSetName;
    }

    public void setItemSetName(String itemSetName) {
        this.itemSetName = itemSetName;
    }

    public String getItemSetStickerNumber() {
        return itemSetStickerNumber;
    }

    public void setItemSetStickerNumber(String itemSetStickerNumber) {
        this.itemSetStickerNumber = itemSetStickerNumber;
    }

    public String getItemSetRegisterDate() {
        return itemSetRegisterDate;
    }

    public void setItemSetRegisterDate(String itemSetRegisterDate) {
        this.itemSetRegisterDate = itemSetRegisterDate;
    }

    public String getItemSetShutDate() {
        return itemSetShutDate;
    }

    public void setItemSetShutDate(String itemSetShutDate) {
        this.itemSetShutDate = itemSetShutDate;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}

