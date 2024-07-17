package NateGroup.StoreFront.Products.Shirts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ShirtService {

        @Autowired
        private ShirtRepository shirtRepository;

        public List<Shirt> getAllShirts() {
            return shirtRepository.findAll();
        }

        public Optional<Shirt> getShirtById(Long id) {
            return shirtRepository.findById(id);
        }

        public Shirt saveShirt(Shirt shirt) {
            return shirtRepository.save(shirt);
        }

        public void deleteShirt(Long id) {
            shirtRepository.deleteById(id);
        }

    public Shirt updateShirt(Long id, Shirt shirtDetails) {
        Optional<Shirt> optionalShirt = shirtRepository.findById(id);
        if (optionalShirt.isPresent()) {
            Shirt shirt = optionalShirt.get();
            shirt.setColor(shirtDetails.getColor());
            shirt.setSize(shirtDetails.getSize());
            return shirtRepository.save(shirt);
        } else {
            throw new RuntimeException("Shirt not found with id " + id);
        }
    }


    }

