package NateGroup.StoreFront.Products.Pants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pants")
public class PantsController {

    @Autowired
    private PantsService pantsService;

    @GetMapping
    public List<Pants> getAllPants() {
        return pantsService.getAllPants();
    }

    @GetMapping("/{id}")
    public Optional<Pants> getPantsById(@PathVariable Long id) {
        return pantsService.getPantsById(id);
    }

    @PostMapping
    public Pants createPants(@RequestBody Pants pants) {
        return pantsService.savePants(pants);
    }

    @DeleteMapping("/{id}")
    public void deletePants(@PathVariable Long id) {
        pantsService.deletePants(id);
    }
}