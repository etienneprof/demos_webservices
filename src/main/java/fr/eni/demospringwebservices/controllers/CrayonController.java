package fr.eni.demospringwebservices.controllers;

import fr.eni.demospringwebservices.entities.Crayon;
import fr.eni.demospringwebservices.services.CrayonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crayons")
public class CrayonController {
    private final CrayonService crayonService;

    @Autowired
    public CrayonController(CrayonService crayonService) {
        this.crayonService = crayonService;
    }

    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<List<Crayon>> findAll() {
        var list = crayonService.findAll();

        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(this.crayonService.findAll());
            // return ResponseEntity.status(HttpStatus.OK).body(this.crayonService.findAll());
        }
    }

    @GetMapping("/{id:\\d+}") // la regex permet de laisser libre les autres urls de /crayons/*
    public ResponseEntity<Crayon> findById(@PathVariable int id) {
        var crayon = crayonService.findById(id);

        if (crayon == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(crayon);
    }

    @PostMapping
    public ResponseEntity<Crayon> save(@RequestBody Crayon crayon) {
        if (crayon.getId() != null && crayon.getId() >= 0) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        var newCrayon = crayonService.save(crayon);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCrayon);
    }

}
