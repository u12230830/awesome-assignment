package dto;

import java.math.BigDecimal;

public class LineItemDto {
    private Long id;
    private Long quantity;
    private String description;
    private BigDecimal unitPrice;
    private BigDecimal lineItemTotal;

    public LineItemDto() {
    }

    public LineItemDto(Long aQuantity, String aDescription, BigDecimal aUnitPrice) {
        quantity = aQuantity;
        description = aDescription;
        unitPrice = aUnitPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public LineItemDto(Long aId, Long aQuantity, String aDescription, BigDecimal aUnitPrice) {
        id = aId;
        quantity = aQuantity;
        description = aDescription;
        unitPrice = aUnitPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        getLineItemTotal();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long aId) {
        id = aId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long aQuantity) {
        quantity = aQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String aDescription) {
        description = aDescription;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void setUnitPrice(BigDecimal aUnitPrice) {
        unitPrice = aUnitPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getLineItemTotal() {
        BigDecimal total = unitPrice.multiply(new BigDecimal(quantity));
        lineItemTotal = total.setScale(2, BigDecimal.ROUND_HALF_UP);
        return lineItemTotal;
    }
}
