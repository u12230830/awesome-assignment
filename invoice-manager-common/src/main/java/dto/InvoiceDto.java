package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceDto {
    private Long Id;
    private String clientName;
    private Long vatRate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date invoiceDate;
    private List<LineItemDto> lineItems;
    private BigDecimal subTotal;
    private BigDecimal vat;
    private BigDecimal total;

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
        return invoiceDate;
    }

    public void setInvoiceDate(Date aInvoiceDate) {
        invoiceDate = aInvoiceDate;
    }

    public List<LineItemDto> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItemDto> aLineItems) {
        lineItems = aLineItems;
    }

    public BigDecimal getSubTotal() {
        return subTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void setSubTotal(BigDecimal aSubTotal) {
        subTotal = aSubTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getVat() {
        return vat.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void setVat(BigDecimal aVat) {
        vat = aVat.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getTotal() {
        return total.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void setTotal(BigDecimal aTotal) {
        total = aTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void addLineItem(Long id, Long quantity, String description, BigDecimal unitPrice) {
        lineItems.add(new LineItemDto(id, quantity, description, unitPrice));
    }
}
