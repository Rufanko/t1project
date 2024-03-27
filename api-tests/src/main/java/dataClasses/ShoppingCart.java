package dataClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {
    @JsonProperty("cart")
    private List<ProductCard> productCard;
    @JsonProperty("total_price")
    private String totalPrice;
    @JsonProperty("total_discount")
    private String totalDiscount;
}
