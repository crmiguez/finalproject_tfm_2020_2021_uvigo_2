package com.crmiguez.aixinainventory.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "invoice")
@Table
public class Invoice implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "invoiceId")
    private Long invoiceId;

    @Column(name = "invoiceName")
    private String invoiceName;

    @Column(name = "invoiceAbbreviation")
    private String invoiceAbbreviation;

    @Column(name = "invoiceDate")
    private String invoiceDate;

    @Column(name = "invoiceAttach")
    @Lob
    private String invoiceAttach;

    public Invoice() {
    }

    public Invoice(Long invoiceId, String invoiceName, String invoiceAbbreviation, String invoiceDate, String invoiceAttach) {
        this.invoiceId = invoiceId;
        this.invoiceName = invoiceName;
        this.invoiceAbbreviation = invoiceAbbreviation;
        this.invoiceDate = invoiceDate;
        this.invoiceAttach = invoiceAttach;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    public String getInvoiceAbbreviation() {
        return invoiceAbbreviation;
    }

    public void setInvoiceAbbreviation(String invoiceAbbreviation) {
        this.invoiceAbbreviation = invoiceAbbreviation;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceAttach() {
        return invoiceAttach;
    }

    public void setInvoiceAttach(String invoiceAttach) {
        this.invoiceAttach = invoiceAttach;
    }
}
