package com.crmiguez.aixinainventory.repository;

import com.crmiguez.aixinainventory.entities.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("invoiceRepository")
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
    Invoice findByInvoiceId(Long invoiceId);
}
