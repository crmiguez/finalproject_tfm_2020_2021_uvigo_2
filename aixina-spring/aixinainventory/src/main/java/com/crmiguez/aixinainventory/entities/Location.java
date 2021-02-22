package com.crmiguez.aixinainventory.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "location")
@Table
public class Location implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "locationId")
    private Long locationId;

    @JoinColumn(name = "departmentLocationId", referencedColumnName = "departmentId", nullable = false)
    @ManyToOne
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Department department;

    @Column(name = "locationAbbreviation")
    private String locationAbbreviation;

    @Column(name = "locationDescription")
    private String locationDescription;

    public Location() {
    }

    public Location(Long locationId, Department department, String locationAbbreviation, String locationDescription) {
        this.locationId = locationId;
        this.department = department;
        this.locationAbbreviation = locationAbbreviation;
        this.locationDescription = locationDescription;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getLocationAbbreviation() {
        return locationAbbreviation;
    }

    public void setLocationAbbreviation(String locationAbbreviation) {
        this.locationAbbreviation = locationAbbreviation;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }
}
