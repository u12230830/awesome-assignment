package dto;

import java.math.BigDecimal;

public class LineItemDto {
    private Long id;
    private Long quantity;
    private Long description;
    private BigDecimal unitPrice;

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

    public Long getDescription() {
        return description;
    }

    public void setDescription(Long aDescription) {
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
        return total.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
