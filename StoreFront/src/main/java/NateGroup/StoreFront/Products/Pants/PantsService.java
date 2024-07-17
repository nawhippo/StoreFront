package NateGroup.StoreFront.Products.Pants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PantsService {
    @Autowired
    private PantsRepository pantsRepository;

    public List<Pants> getAllPants() {
        return pantsRepository.findAll();
    }

    public Optional<Pants> getPantsById(Long id) {
        return pantsRepository.findById(id);
    }

    public Pants savePants(Pants pants) {
        return pantsRepository.save(pants);
    }

    public void deletePants(Long id) {
        pantsRepository.deleteById(id);
    }
}
