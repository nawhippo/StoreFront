package NateGroup.StoreFront.Products.Shirts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shirts")
public class ShirtController {

    @Autowired
    private ShirtService shirtService;

    @GetMapping
    public List<Shirt> getAllShirts() {
        return shirtService.getAllShirts();
    }

    @GetMapping("/{id}")
    public Optional<Shirt> getShirtById(@PathVariable Long id) {
        return shirtService.getShirtById(id);
    }

    @PostMapping
    public Shirt createShirt(@RequestBody Shirt shirt) {
        return shirtService.saveShirt(shirt);
    }

    @DeleteMapping("/{id}")
    public void deleteShirt(@PathVariable Long id) {
        shirtService.deleteShirt(id);
    }

    @PutMapping("/{id}")
    public Shirt updateShirt(@PathVariable Long id, @RequestBody Shirt shirtDetails) {
        return shirtService.updateShirt(id, shirtDetails);

    }
}