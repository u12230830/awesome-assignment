package endpoint;

import dto.AddInvoiceRequest;
import dto.InvoiceDto;
import endpoint.util.InvoiceManagerEndpointHelper;
import model.Invoice;
import model.LineItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import repo.InvoiceRepository;
import repo.LineItemRespository;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private LineItemRespository lineItemRespository;

    /**
     * Returns required invoice by Id
     *
     * @param invoiceId
     * @return
     */
    @RequestMapping(value = "/{invoiceId}", method = RequestMethod.GET)
    public InvoiceDto viewInvoice(@RequestParam(value = "invoiceId") Long invoiceId) {
        Invoice invoice = invoiceRepository.getOne(invoiceId);
        return InvoiceManagerEndpointHelper.buildInvoiceDtoFromInvoice(invoice);
    }

    /**
     * Returns a list of all stored invoices
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<InvoiceDto> viewAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return InvoiceManagerEndpointHelper.buildInvoiceDtosFromInvoiceList(invoices);
    }

    /**
     * Stores an invoice to the database
     * @param aAddInvoiceRequest
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public InvoiceDto addInvoice(@RequestBody AddInvoiceRequest aAddInvoiceRequest) {
        Invoice invoice = InvoiceManagerEndpointHelper.buildInvoiceFromAddInvoiceRequest(aAddInvoiceRequest);
        invoiceRepository.save(invoice);
        InvoiceDto invoiceDto = InvoiceManagerEndpointHelper.buildInvoiceDtoFromInvoice(invoice);
        saveItemItems(invoice);
        return invoiceDto;
    }

    private void saveItemItems(Invoice aInvoice){
        for(LineItem lineItem : aInvoice.getLineItems()){
            lineItem.setInvoice(aInvoice);
            lineItemRespository.save(lineItem);
        }
    }
}