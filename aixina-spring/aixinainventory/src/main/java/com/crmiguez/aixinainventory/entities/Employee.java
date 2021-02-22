package com.crmiguez.aixinainventory.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "employee")
@Table
public class Employee implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "employeeId")
    private Long employeeId;

    @Column(name = "employeeName")
    private String employeeName;

    @Column(name = "employeeLastNames")
    private String employeeLastNames;

    @Column(name = "employeeNIF")
    private String employeeNIF;

    @JoinColumn(name = "departmentId", referencedColumnName = "departmentId")
    @ManyToOne
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Department department;

    @JoinColumn(name = "locationId", referencedColumnName = "locationId")
    @ManyToOne
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Location location;

    @JoinColumn(name = "employeeChiefId", referencedColumnName = "employeeId")
    @ManyToOne
    private Employee employeeChief;

    @JoinColumn(name = "itemSetId", referencedColumnName = "itemSetId")
    @ManyToOne
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    private ItemSet itemSet;

    @JoinColumn(name = "itemId", referencedColumnName = "itemId")
    @ManyToOne
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    private Item item;

    @Column(name = "employeeRegisterDate")
    private String employeeRegisterDate;

    @Column(name = "employeeShutDate")
    private String employeeShutDate;

    @OneToMany(mappedBy = "employee")
    @org.springframework.data.annotation.Transient
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    public Employee() {
    }

    public Employee(Long employeeId, String employeeName, String employeeLastNames, String employeeNIF, Location location, Department department, Employee employeeChief, ItemSet itemSet, Item item, String employeeRegisterDate, String employeeShutDate) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeLastNames = employeeLastNames;
        this.employeeNIF = employeeNIF;
        this.department = department;
        this.location = location;
        this.employeeChief = employeeChief;
        this.itemSet = itemSet;
        this.item = item;
        this.employeeRegisterDate = employeeRegisterDate;
        this.employeeShutDate = employeeShutDate;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeLastNames() {
        return employeeLastNames;
    }

    public void setEmployeeLastNames(String employeeLastNames) {
        this.employeeLastNames = employeeLastNames;
    }

    public String getEmployeeNIF() {
        return employeeNIF;
    }

    public void setEmployeeNIF(String employeeNIF) {
        this.employeeNIF = employeeNIF;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getEmployeeChief() {
        return employeeChief;
    }

    public void setEmployeeChief(Employee employeeChief) {
        this.employeeChief = employeeChief;
    }

    public ItemSet getItemSet() {
        return itemSet;
    }

    public void setItemSet(ItemSet itemSet) {
        this.itemSet = itemSet;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getEmployeeRegisterDate() {
        return employeeRegisterDate;
    }

    public void setEmployeeRegisterDate(String employeeRegisterDate) {
        this.employeeRegisterDate = employeeRegisterDate;
    }

    public String getEmployeeShutDate() {
        return employeeShutDate;
    }

    public void setEmployeeShutDate(String employeeShutDate) {
        this.employeeShutDate = employeeShutDate;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
