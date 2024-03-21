package dateClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCard {
    private String category;
    private String discount;
    @JsonProperty(required = false)
    private String id;
    private String name;
    private String price;

}
