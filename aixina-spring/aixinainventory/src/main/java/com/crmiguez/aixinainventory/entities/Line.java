package com.crmiguez.aixinainventory.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "line")
@Table
public class Line implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "lineId")
    private Long lineId;

    @JoinColumn(name = "invoiceId", referencedColumnName = "invoiceId", nullable = false)
    @ManyToOne
    private Invoice invoice;

    @JoinColumn(name = "itemId", referencedColumnName = "itemId")
    @ManyToOne
    private Item item;

    @JoinColumn(name = "itemSetId", referencedColumnName = "itemSetId")
    @ManyToOne
    private ItemSet itemSet;

    @Column(name = "units")
    private Long units;

    @Column(name = "invoiceUnitCost")
    private Double invoiceUnitCost;

    @Column(name = "invoiceTotalCost")
    private Double invoiceTotalCost;

    public Line() {
    }

    public Line(Long lineId, Invoice invoice, Item item, ItemSet itemSet, Long units, Double invoiceUnitCost, Double invoiceTotalCost) {
        this.lineId = lineId;
        this.invoice = invoice;
        this.item = item;
        this.itemSet = itemSet;
        this.units = units;
        this.invoiceUnitCost = invoiceUnitCost;
        this.invoiceTotalCost = invoiceTotalCost;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ItemSet getItemSet() {
        return itemSet;
    }

    public void setItemSet(ItemSet itemSet) {
        this.itemSet = itemSet;
    }

    public Long getUnits() {
        return units;
    }

    public void setUnits(Long units) {
        this.units = units;
    }

    public Double getInvoiceUnitCost() {
        return invoiceUnitCost;
    }

    public void setInvoiceUnitCost(Double invoiceUnitCost) {
        this.invoiceUnitCost = invoiceUnitCost;
    }

    public Double getInvoiceTotalCost() {
        return invoiceTotalCost;
    }

    public void setInvoiceTotalCost(Double invoiceTotalCost) {
        this.invoiceTotalCost = invoiceTotalCost;
    }
}
