package endpoint.util;

import dto.AddInvoiceRequest;
import dto.InvoiceDto;
import model.Invoice;
import model.LineItem;

import java.util.ArrayList;
import java.util.List;

public class InvoiceManagerEndpointHelper {

    public static List<InvoiceDto> buildInvoiceDtosFromInvoiceList(List<Invoice> aInvoices) {
        List<InvoiceDto> invoiceDtos = new ArrayList<>();
        for (Invoice invoice : aInvoices) {
            invoiceDtos.add(buildInvoiceDtoFromInvoice(invoice));
        }
        return invoiceDtos;
    }


    public static InvoiceDto buildInvoiceDtoFromInvoice(Invoice aInvoice) {
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

    public static Invoice buildInvoiceFromAddInvoiceRequest(AddInvoiceRequest aAddInvoiceRequest) {
        Invoice invoice = new Invoice();
        invoice.setClientName(aAddInvoiceRequest.getClientName());
        invoice.setInvoiceDate(aAddInvoiceRequest.getInvoiceDate());
        invoice.setVatRate(aAddInvoiceRequest.getVatRate());
        return invoice;
    }
}
