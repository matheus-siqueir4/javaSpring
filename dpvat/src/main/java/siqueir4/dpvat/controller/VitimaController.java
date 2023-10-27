package siqueir4.dpvat.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import siqueir4.dpvat.ServiceImplements.VitimaServiceImpl;
import siqueir4.dpvat.models.Vitima;

@RestController
@RequestMapping("api/vitima")
@Validated
public class VitimaController {
    
    @Autowired
    VitimaServiceImpl service;

    @GetMapping
    public ResponseEntity<List<Vitima>> getAllVitima() {
        var vitimas = this.service.getAllVitima();
        return ResponseEntity.ok().body(vitimas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vitima> getByIdVitima(@PathVariable UUID id) {
        var vitima = this.service.findByIdVitima(id);
        return ResponseEntity.ok().body(vitima);
    }

    @PostMapping
    public ResponseEntity<Vitima> createVitima(@Valid @RequestBody Vitima vitima) {
        this.service.createVitima(vitima);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
        .buildAndExpand(vitima.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<Vitima> updateVitima(@Valid @RequestBody Vitima vitima, @PathVariable UUID id) {
        vitima.setId(id);
        this.service.updateVitima(vitima);

        return ResponseEntity.noContent().build();
    }
}
