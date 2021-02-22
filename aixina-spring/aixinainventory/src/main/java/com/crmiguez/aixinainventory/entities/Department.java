package com.crmiguez.aixinainventory.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "department")
@Table
public class Department implements Serializable {

    @Id
    @Size(max = 50)
    @Column(name = "departmentId")
    private String departmentId;

    @Column(name = "departmentName")
    private String departmentName;

    @JoinColumn(name = "headquartersId", referencedColumnName = "headquartersId", nullable = false)
    @ManyToOne
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Headquarters headquarters;

    @OneToMany (mappedBy = "department")
    @org.springframework.data.annotation.Transient
    @JsonIgnore
    Set<Location> locations = new HashSet<>();

    public Department() {
    }

    public Department(@Size(max = 50) String departmentId, String departmentName, Headquarters headquarters) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.headquarters = headquarters;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Headquarters getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(Headquarters headquarters) {
        this.headquarters = headquarters;
    }
}
