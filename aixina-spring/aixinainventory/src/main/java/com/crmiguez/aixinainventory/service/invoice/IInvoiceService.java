package com.crmiguez.aixinainventory.service.invoice;

import com.crmiguez.aixinainventory.entities.Invoice;

import java.util.List;
import java.util.Optional;

public interface IInvoiceService {
    public List<Invoice> findAllInvoices();
    public Optional<Invoice> findInvoiceById(Long invoiceId);
    public Invoice addInvoice(Invoice invoice);
    public Invoice updateInvoice(Invoice invoiceDetails, Invoice invoice);
    public void deleteInvoice (Invoice invoice);

}
