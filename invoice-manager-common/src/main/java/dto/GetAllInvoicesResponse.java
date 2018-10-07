package dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetAllInvoicesResponse {
    List<InvoiceDto> invoiceDtos;

    public GetAllInvoicesResponse() {
        invoiceDtos = new ArrayList<InvoiceDto>();
    }

    public List<InvoiceDto> getInvoiceDtos() {
        return invoiceDtos;
    }

    public void setInvoiceDtos(List<InvoiceDto> aInvoiceDtos) {
        invoiceDtos = aInvoiceDtos;
    }

    public void addInvoice(Long id, Date invoiceDate, String clientName, Long vatRate) {
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setId(id);
        invoiceDto.setClientName(clientName);
        invoiceDto.setVatRate(vatRate);
        invoiceDto.setInvoiceDate(invoiceDate);
    }
}
