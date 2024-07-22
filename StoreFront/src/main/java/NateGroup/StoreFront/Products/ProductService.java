package NateGroup.StoreFront.Products;

import NateGroup.StoreFront.Products.Pants.Pants;
import NateGroup.StoreFront.Products.Pants.PantsRepository;
import NateGroup.StoreFront.Products.Shirts.Shirt;
import NateGroup.StoreFront.Products.Shirts.ShirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ShirtRepository shirtRepository;
    @Autowired
    PantsRepository pantsRepository;
    public List<Product> getAllProducts(){
        List<Product> allProducts = new ArrayList<>();
        List<Pants> pants = pantsRepository.findAll();
        List<Shirt> shirts = shirtRepository.findAll();
        allProducts.addAll(pants);
        allProducts.addAll(shirts);
        return allProducts;
    }
}
