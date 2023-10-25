package macrobrang.dpvat.Controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import macrobrang.dpvat.Models.Contato;
import macrobrang.dpvat.ServiceImplements.ContatoServiceImpl;

@RestController
@RequestMapping("/api/contato")
@Validated
public class ContatoController {
    
    @Autowired
    private ContatoServiceImpl service;

    @GetMapping
    public ResponseEntity<List<Contato>> getAllContato() {
        var contatos = this.service.getAllContato();
        return ResponseEntity.ok().body(contatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> findById(@PathVariable UUID id) {
        Contato contato = this.service.findByIdContato(id);
        return ResponseEntity.ok().body(contato);
    }

    @PostMapping
    public ResponseEntity<Void> createContato(@Valid @RequestBody Contato contato) {
        this.service.createContato(contato);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
        .buildAndExpand(contato.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateContato(@Valid @RequestBody Contato contato, @PathVariable UUID id) {
        contato.setId(id);
        this.service.updateContato(contato);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContato(@PathVariable UUID id) {
        this.service.deleteContato(id);
        return ResponseEntity.noContent().build();
    }
}
