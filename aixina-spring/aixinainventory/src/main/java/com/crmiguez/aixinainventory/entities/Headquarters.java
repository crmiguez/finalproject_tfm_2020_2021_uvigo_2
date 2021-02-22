package com.crmiguez.aixinainventory.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "headquarters")
@Table
public class Headquarters implements Serializable {

    @Id
    @Size(max = 50)
    @Column(name = "headquartersId")
    private String headquartersId;

    @Column(name = "headquartersName")
    private String headquartersName;

    @Column(name = "headquartersAddress")
    private String headquartersAddress;

    @Column(name = "headquartersLocation")
    private String headquartersLocation;

    @Column(name = "headquartersWebSite")
    private String headquartersWebSite;

    public Headquarters() {
    }

    public Headquarters(@Size(max = 50) String headquartersId, String headquartersName, String headquartersAddress, String headquartersLocation, String headquartersWebSite) {
        this.headquartersId = headquartersId;
        this.headquartersName = headquartersName;
        this.headquartersAddress = headquartersAddress;
        this.headquartersLocation = headquartersLocation;
        this.headquartersWebSite = headquartersWebSite;
    }

    public String getHeadquartersId() {
        return headquartersId;
    }

    public void setHeadquartersId(String headquartersId) {
        this.headquartersId = headquartersId;
    }

    public String getHeadquartersName() {
        return headquartersName;
    }

    public void setHeadquartersName(String headquartersName) {
        this.headquartersName = headquartersName;
    }

    public String getHeadquartersAddress() {
        return headquartersAddress;
    }

    public void setHeadquartersAddress(String headquartersAddress) {
        this.headquartersAddress = headquartersAddress;
    }

    public String getHeadquartersLocation() {
        return headquartersLocation;
    }

    public void setHeadquartersLocation(String headquartersLocation) {
        this.headquartersLocation = headquartersLocation;
    }

    public String getHeadquartersWebSite() {
        return headquartersWebSite;
    }

    public void setHeadquartersWebSite(String headquartersWebSite) {
        this.headquartersWebSite = headquartersWebSite;
    }
}
