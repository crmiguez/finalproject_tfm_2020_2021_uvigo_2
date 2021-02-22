package com.crmiguez.aixinainventory.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "itemtype")
@Table
public class ItemType implements Serializable {

    @Id
    @Size(max = 50)
    @Column(name = "itemTypeId")
    private String itemTypeId;

    @Column(name = "itemTypeName")
    private String itemTypeName;

    public ItemType() {
    }

    public ItemType(@Size(max = 50) String itemTypeId, String itemTypeName) {
        this.itemTypeId = itemTypeId;
        this.itemTypeName = itemTypeName;
    }

    public String getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(String itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }
}
