package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "INVOICE")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String clientName;
    private Long vatRate;
    private Date invoiceDate;
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<LineItem> lineItems;

    public Long getId() {
        return id;
    }

    public void setId(Long aId) {
        id = aId;
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

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> aLineItems) {
        lineItems = aLineItems;
    }

    public BigDecimal getSubTotal() {
        BigDecimal subTotal = BigDecimal.valueOf(0);
        for (LineItem lineItem : lineItems) {
            subTotal = subTotal.add(lineItem.getLineItemTotal());
        }

        return subTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getVat() {
        return getSubTotal().multiply(BigDecimal.valueOf(vatRate).divide(BigDecimal.valueOf(100))).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getTotal() {
        return getSubTotal().add(getVat()).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
