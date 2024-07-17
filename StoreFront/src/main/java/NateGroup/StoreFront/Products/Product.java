package NateGroup.StoreFront.Products;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    Long id;
    Long price;
    int quantity;
    String color;
    String size;
}
