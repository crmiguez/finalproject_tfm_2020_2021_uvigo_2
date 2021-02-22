package com.crmiguez.aixinainventory.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "movetype")
@Table
public class MoveType implements Serializable {

    @Id
    @Size(max = 50)
    @Column(name = "moveTypeId")
    private String moveTypeId;

    @Column(name = "moveTypeName")
    private String moveTypeName;

    @Column(name = "moveTypeDescription")
    private String moveTypeDescription;

    public MoveType() {
    }

    public MoveType(@Size(max = 50) String moveTypeId) {
        this.moveTypeId = moveTypeId;
    }

    public MoveType(@Size(max = 50) String moveTypeId, String moveTypeName, String moveTypeDescription) {
        this.moveTypeId = moveTypeId;
        this.moveTypeName = moveTypeName;
        this.moveTypeDescription = moveTypeDescription;
    }

    public String getMoveTypeId() {
        return moveTypeId;
    }

    public void setMoveTypeId(String moveTypeId) {
        this.moveTypeId = moveTypeId;
    }

    public String getMoveTypeName() {
        return moveTypeName;
    }

    public void setMoveTypeName(String moveTypeName) {
        this.moveTypeName = moveTypeName;
    }

    public String getMoveTypeDescription() {
        return moveTypeDescription;
    }

    public void setMoveTypeDescription(String moveTypeDescription) {
        this.moveTypeDescription = moveTypeDescription;
    }
}
