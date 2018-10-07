package endpoint;

import dto.AddInvoiceRequest;
import model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import repo.InvoiceRepository;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @RequestMapping("/{invoiceId}")
    public AddInvoiceRequest getInvoice(@RequestParam(value = "invoiceId") String invoiceId) {
        return new AddInvoiceRequest();
    }

    @RequestMapping("/")
    public List<Invoice> getAllInvoices(){
        return invoiceRepository.findAll();
    }

    @RequestMapping(value = "/{invoiceId}", method = RequestMethod.POST)
    public AddInvoiceRequest addInvoice(@RequestBody AddInvoiceRequest aAddInvoiceRequest) {
        Invoice invoice = new Invoice();
        invoice.setClientName(aAddInvoiceRequest.getClientName());
        invoice.setInvoiceDate(aAddInvoiceRequest.getInvoiceDate());
        invoice.setVatRate(aAddInvoiceRequest.getVatRate());

        Invoice responseInvoice = invoiceRepository.save(invoice);
        aAddInvoiceRequest.setId(responseInvoice.getId());
        return aAddInvoiceRequest;
    }
}