package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class AddInvoiceRequest {
    private String clientName;
    private BigDecimal vatRate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date InvoiceDate;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String aClientName) {
        clientName = aClientName;
    }

    public BigDecimal getVatRate() {
        return vatRate.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void setVatRate(BigDecimal aVatRate) {
        vatRate = aVatRate.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public Date getInvoiceDate() {
        return InvoiceDate;
    }

    public void setInvoiceDate(Date aInvoiceDate) {
        InvoiceDate = aInvoiceDate;
    }
}
