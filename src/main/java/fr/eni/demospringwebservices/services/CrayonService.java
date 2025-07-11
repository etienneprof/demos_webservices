package fr.eni.demospringwebservices.services;

import fr.eni.demospringwebservices.entities.Crayon;
import fr.eni.demospringwebservices.repositories.CrayonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CrayonService {
    private CrayonRepository crayonRepository;

    @Autowired
    public CrayonService(CrayonRepository crayonRepository) {
        this.crayonRepository = crayonRepository;

        List<Crayon> bouchon = Arrays.asList(
                Crayon.builder().type("stylo bic").couleur("bleu").build(),
                Crayon.builder().type("stylo plume").couleur("noir").build(),
                Crayon.builder().type("crayon de couleur").couleur("rose").build(),
                Crayon.builder().type("crayon de couleur").couleur("jaune").build(),
                Crayon.builder().type("fluo").couleur("vert").build(),
                Crayon.builder().type("fluo").couleur("bleu").build(),
                Crayon.builder().type("fluo").couleur("rose").build()
        );

        crayonRepository.saveAll(bouchon);
    }

    public List<Crayon> findAll() {
        return this.crayonRepository.findAll();
    }

    public Crayon findById(int id) {
        return this.crayonRepository.findById(id).orElse(null);
    }
}
