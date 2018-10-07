package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "LINE_ITEM")
public class LineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long quantity;
    private String description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String aDescription) {
        description = aDescription;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal aUnitPrice) {
        unitPrice = aUnitPrice;
    }
}
