package com.crmiguez.aixinainventory.apirest;

import com.crmiguez.aixinainventory.entities.Invoice;
import com.crmiguez.aixinainventory.exception.ResourceNotFoundException;
import com.crmiguez.aixinainventory.service.invoice.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/api_aixina/v1")
@RequestMapping("/api_aixina/v1/invoicemanage")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class InvoiceController {

    @Autowired
    @Qualifier("invoiceService")
    private IInvoiceService invoiceService;

    @PreAuthorize("hasAuthority('PERM_READ_ALL_INVOICES')")
    @GetMapping("/invoices")
    public List<Invoice> getAllInvoices() {
        return invoiceService.findAllInvoices();
    }

    @PreAuthorize("hasAuthority('PERM_READ_INVOICE')")
    @GetMapping("/invoices/{id}")
    public ResponseEntity<Invoice> getInvoiceById(
            @PathVariable(value = "id") Long invoiceId) throws ResourceNotFoundException {
        Invoice invoice = invoiceService.findInvoiceById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found on :: "+ invoiceId));
        return ResponseEntity.ok().body(invoice);
    }

    @PreAuthorize("hasAuthority('PERM_CREATE_INVOICE')")
    @PostMapping("/invoice")
    public Invoice createInvoice(@Valid @RequestBody Invoice invoice) { return invoiceService.addInvoice(invoice); }

    @PreAuthorize("hasAuthority('PERM_UPDATE_INVOICE')")
    @PutMapping("/invoices/{id}")
    public ResponseEntity<Invoice> updateInvoice(
            @PathVariable(value = "id") Long invoiceId,
            @Valid @RequestBody Invoice invoiceDetails) throws ResourceNotFoundException {
        Invoice invoice = invoiceService.findInvoiceById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found on :: "+ invoiceId));

        final Invoice updatedInvoice = invoiceService.updateInvoice(invoiceDetails, invoice);
        if  (updatedInvoice == null){
            return new ResponseEntity<Invoice>(HttpStatus.EXPECTATION_FAILED);
        } else {
            return ResponseEntity.ok(updatedInvoice);
        }
    }

    @PreAuthorize("hasAuthority('PERM_DELETE_INVOICE')")
    @DeleteMapping("/invoice/{id}")
    public Map<String, Boolean> deleteInvoice(
            @PathVariable(value = "id") Long invoiceId) throws Exception {
        Invoice invoice = invoiceService.findInvoiceById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found on :: "+ invoiceId));

        invoiceService.deleteInvoice(invoice);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
