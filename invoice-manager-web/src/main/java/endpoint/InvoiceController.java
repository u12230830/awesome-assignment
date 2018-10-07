package endpoint;

import dto.AddInvoiceRequest;
import dto.InvoiceDto;
import model.Invoice;
import model.LineItem;
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
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @RequestMapping(value = "/{invoiceId}", method = RequestMethod.POST)
    public InvoiceDto addInvoice(@RequestBody AddInvoiceRequest aAddInvoiceRequest) {
        Invoice invoice = buildInvoiceFromAddInvoiceRequest(aAddInvoiceRequest);
        invoiceRepository.save(invoice);
        InvoiceDto invoiceDto = buildInvoiceDtoFromInvoice(invoice);
        return invoiceDto;
    }

    private InvoiceDto buildInvoiceDtoFromInvoice(Invoice aInvoice) {
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setId(aInvoice.getId());
        invoiceDto.setInvoiceDate(aInvoice.getInvoiceDate());
        invoiceDto.setClientName(aInvoice.getClientName());
        invoiceDto.setVatRate(aInvoice.getVatRate());
        for (LineItem lineItem : aInvoice.getLineItems()) {
            invoiceDto.addLineItem(lineItem.getId(), lineItem.getQuantity(), lineItem.getDescription(), lineItem.getUnitPrice());
        }

        return invoiceDto;
    }

    private Invoice buildInvoiceFromAddInvoiceRequest(AddInvoiceRequest aAddInvoiceRequest) {
        Invoice invoice = new Invoice();
        invoice.setClientName(aAddInvoiceRequest.getClientName());
        invoice.setInvoiceDate(aAddInvoiceRequest.getInvoiceDate());
        invoice.setVatRate(aAddInvoiceRequest.getVatRate());
        return invoice;
    }
}