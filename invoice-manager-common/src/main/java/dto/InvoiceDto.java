package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceDto {
    private Long Id;
    private String clientName;
    private Long vatRate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date InvoiceDate;
    private List<LineItemDto> lineItems;

    public InvoiceDto() {
        lineItems = new ArrayList<LineItemDto>();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long aId) {
        Id = aId;
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

    public List<LineItemDto> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItemDto> aLineItems) {
        lineItems = aLineItems;
    }

    public void addLineItem(LineItemDto aLineItemDto) {
        lineItems.add(aLineItemDto);
    }
}
