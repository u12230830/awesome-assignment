package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddInvoiceRequest {
    private String clientName;
    private Long vatRate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date InvoiceDate;
    private List<LineItemDto> lineItemDtos;

    public AddInvoiceRequest() {
        lineItemDtos = new ArrayList<LineItemDto>();
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String aClientName) {
        clientName = aClientName;
    }

    public Long getVatRate() {
        return vatRate;
    }

    public void setVatRate(Long aVatRate) {
        vatRate = aVatRate;
    }

    public Date getInvoiceDate() {
        return InvoiceDate;
    }

    public void setInvoiceDate(Date aInvoiceDate) {
        InvoiceDate = aInvoiceDate;
    }

    public List<LineItemDto> getLineItemDtos() {
        return lineItemDtos;
    }

    public void setLineItemDtos(List<LineItemDto> aLineItemDtos) {
        lineItemDtos = aLineItemDtos;
    }
}
