package com.crmiguez.aixinainventory.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "inventory")
@Table
public class Inventory implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "inventoryId")
    private Long inventoryId;

    @Column(name = "inventoryDate")
    private String inventoryDate;

    @Column(name = "inventoryYear")
    private String inventoryYear;

    @Column(name = "inventoryControlNumber")
    private String inventoryControlNumber;

    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId")
    @ManyToOne
    private Employee employee;

    @JoinColumn(name = "headquartersId", referencedColumnName = "headquartersId", nullable = false)
    @ManyToOne
    private Headquarters headquarters;

    @JoinColumn(name = "invoiceId", referencedColumnName = "invoiceId", nullable = false)
    @ManyToOne
    private Invoice invoice;

    @JoinColumn(name = "itemSetId", referencedColumnName = "itemSetId")
    @ManyToOne
    private ItemSet itemSet;

    @JoinColumn(name = "itemId", referencedColumnName = "itemId")
    @ManyToOne
    private Item item;

    @Column(name = "inventoryUnits")
    private Long inventoryUnits;

    @Column(name = "inventoryUnitCost")
    private Double inventoryUnitCost;

    @Column(name = "inventoryTotalValue")
    private Double inventoryTotalValue;

    @JoinColumn(name = "inventoryLocation", referencedColumnName = "locationId")
    @ManyToOne
    private Location location;

    public Inventory() {
    }

    public Inventory(Long inventoryId, String inventoryDate, String inventoryYear, String inventoryControlNumber, Employee employee, Headquarters headquarters, Invoice invoice, ItemSet itemSet, Item item, Long inventoryUnits, Double inventoryUnitCost, Double inventoryTotalValue, Location location) {
        this.inventoryId = inventoryId;
        this.inventoryDate = inventoryDate;
        this.inventoryYear = inventoryYear;
        this.inventoryControlNumber = inventoryControlNumber;
        this.employee = employee;
        this.headquarters = headquarters;
        this.invoice = invoice;
        this.itemSet = itemSet;
        this.item = item;
        this.inventoryUnits = inventoryUnits;
        this.inventoryUnitCost = inventoryUnitCost;
        this.inventoryTotalValue = inventoryTotalValue;
        this.location = location;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getInventoryDate() {
        return inventoryDate;
    }

    public void setInventoryDate(String inventoryDate) {
        this.inventoryDate = inventoryDate;
    }

    public String getInventoryYear() {
        return inventoryYear;
    }

    public void setInventoryYear(String inventoryYear) {
        this.inventoryYear = inventoryYear;
    }

    public String getInventoryControlNumber() {
        return inventoryControlNumber;
    }

    public void setInventoryControlNumber(String inventoryControlNumber) {
        this.inventoryControlNumber = inventoryControlNumber;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Headquarters getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(Headquarters headquarters) {
        this.headquarters = headquarters;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
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

    public Long getInventoryUnits() {
        return inventoryUnits;
    }

    public void setInventoryUnits(Long inventoryUnits) {
        this.inventoryUnits = inventoryUnits;
    }

    public Double getInventoryUnitCost() {
        return inventoryUnitCost;
    }

    public void setInventoryUnitCost(Double inventoryUnitCost) {
        this.inventoryUnitCost = inventoryUnitCost;
    }

    public Double getInventoryTotalValue() {
        return inventoryTotalValue;
    }

    public void setInventoryTotalValue(Double inventoryTotalValue) {
        this.inventoryTotalValue = inventoryTotalValue;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
