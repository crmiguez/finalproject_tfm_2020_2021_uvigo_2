package com.crmiguez.aixinainventory.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "movement")
@Table
public class Movement implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "movementId")
    private Long movementId;

    @Column(name = "movementDate")
    private String  movementDate;

    @JoinColumn(name = "moveTypeId", referencedColumnName = "moveTypeId", nullable = false)
    @ManyToOne
    private MoveType moveType;

    @JoinColumn(name = "itemSetId", referencedColumnName = "itemSetId")
    @ManyToOne
    private ItemSet itemSet;

    @JoinColumn(name = "itemId", referencedColumnName = "itemId")
    @ManyToOne
    private Item item;

    @JoinColumn(name = "employeeSourceId", referencedColumnName = "employeeId")
    @ManyToOne
    private Employee employeeSource;

    @JoinColumn(name = "employeeDestinationId", referencedColumnName = "employeeId")
    @ManyToOne
    private Employee employeeDestination;

    @JoinColumn(name = "departmentSourceId", referencedColumnName = "departmentId")
    @ManyToOne
    private Department departmentSource;

    @JoinColumn(name = "departmentDestinationId", referencedColumnName = "departmentId")
    @ManyToOne
    private Department departmentDestination;

    @JoinColumn(name = "locationSourceId", referencedColumnName = "locationId")
    @ManyToOne
    private Location locationSource;

    @JoinColumn(name = "locationDestinationId", referencedColumnName = "locationId")
    @ManyToOne
    private Location locationDestination;

    @JoinColumn(name = "invoiceId", referencedColumnName = "invoiceId")
    @ManyToOne
    private Invoice invoice;

    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId")
    @ManyToOne
    private Employee employee;

    @Column(name = "movementUnits")
    private Long movementUnits;

    @Column(name = "movementReason")
    private String movementReason;

    public Movement() {
    }

    public Movement(Long movementId, String movementDate, MoveType moveType, ItemSet itemSet, Item item, Employee employeeSource, Employee employeeDestination, Department departmentSource, Department departmentDestination, Location locationSource, Location locationDestination, Invoice invoice, Employee employee, Long movementUnits, String movementReason) {
        this.movementId = movementId;
        this.movementDate = movementDate;
        this.moveType = moveType;
        this.itemSet = itemSet;
        this.item = item;
        this.employeeSource = employeeSource;
        this.employeeDestination = employeeDestination;
        this.departmentSource = departmentSource;
        this.departmentDestination = departmentDestination;
        this.locationSource = locationSource;
        this.locationDestination = locationDestination;
        this.invoice = invoice;
        this.employee = employee;
        this.movementUnits = movementUnits;
        this.movementReason = movementReason;
    }

    public Long getMovementId() {
        return movementId;
    }

    public void setMovementId(Long movementId) {
        this.movementId = movementId;
    }

    public String getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(String movementDate) {
        this.movementDate = movementDate;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public void setMoveType(MoveType moveType) {
        this.moveType = moveType;
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

    public Employee getEmployeeSource() {
        return employeeSource;
    }

    public void setEmployeeSource(Employee employeeSource) {
        this.employeeSource = employeeSource;
    }

    public Employee getEmployeeDestination() {
        return employeeDestination;
    }

    public void setEmployeeDestination(Employee employeeDestination) {
        this.employeeDestination = employeeDestination;
    }

    public Department getDepartmentSource() {
        return departmentSource;
    }

    public void setDepartmentSource(Department departmentSource) {
        this.departmentSource = departmentSource;
    }

    public Department getDepartmentDestination() {
        return departmentDestination;
    }

    public void setDepartmentDestination(Department departmentDestination) {
        this.departmentDestination = departmentDestination;
    }

    public Location getLocationSource() {
        return locationSource;
    }

    public void setLocationSource(Location locationSource) {
        this.locationSource = locationSource;
    }

    public Location getLocationDestination() {
        return locationDestination;
    }

    public void setLocationDestination(Location locationDestination) {
        this.locationDestination = locationDestination;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getMovementUnits() {
        return movementUnits;
    }

    public void setMovementUnits(Long movementUnits) {
        this.movementUnits = movementUnits;
    }

    public String getMovementReason() {
        return movementReason;
    }

    public void setMovementReason(String movementReason) {
        this.movementReason = movementReason;
    }
}
