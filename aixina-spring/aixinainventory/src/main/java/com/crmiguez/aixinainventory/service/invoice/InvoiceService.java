package com.crmiguez.aixinainventory.service.invoice;

import com.crmiguez.aixinainventory.entities.Invoice;
import com.crmiguez.aixinainventory.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("invoiceService")
public class InvoiceService implements IInvoiceService {

    @Autowired
    @Qualifier("invoiceRepository")
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> findAllInvoices() {
        return (List<Invoice>) invoiceRepository.findAll();
    }

    @Override
    public Optional<Invoice> findInvoiceById(Long invoiceId){
        return invoiceRepository.findById(invoiceId);
    }

    @Override
    public Invoice addInvoice(Invoice invoice){
        return invoiceRepository.save(invoice);
    }

    @Override
    public void deleteInvoice (Invoice invoice){
        invoiceRepository.delete(invoice);
    }

    @Override
    public Invoice updateInvoice(Invoice invoiceDetails, Invoice invoice){

        invoice.setInvoiceName(invoiceDetails.getInvoiceName());
        invoice.setInvoiceAbbreviation(invoiceDetails.getInvoiceAbbreviation());
        invoice.setInvoiceDate(invoiceDetails.getInvoiceDate());
        invoice.setInvoiceAttach(invoiceDetails.getInvoiceAttach());

        return invoiceRepository.save(invoice);
    }

}
