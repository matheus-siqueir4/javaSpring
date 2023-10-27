package siqueir4.dpvat.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

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

import jakarta.validation.Valid;
import siqueir4.dpvat.ServiceImplements.VendedorServiceImpl;
import siqueir4.dpvat.models.Vendedor;

@RestController
@RequestMapping("api/vendedor")
@Validated
public class VendedorController {
    
    @Autowired
    VendedorServiceImpl service;

    @GetMapping
    public ResponseEntity<List<Vendedor>> getAllVendedor() {
        var vendedores = service.getAllVendedor();
        return ResponseEntity.ok().body(vendedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> getById(@PathVariable UUID id) {
        var vendedor = this.service.findbyIdVendedor(id);
        return ResponseEntity.ok().body(vendedor);
    }

    @PostMapping
    public ResponseEntity<Vendedor> createVendedor(@Valid @RequestBody Vendedor vendedor) {
        this.service.createVendedor(vendedor);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
        .buildAndExpand(vendedor.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendedor> updateVendedor(@Valid @RequestBody Vendedor vendedor, @PathVariable UUID id) {
        vendedor.setId(id);
        this.service.updateVendedor(vendedor);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendedor(@PathVariable UUID id) {
        this.service.deleteVendedor(id);
        return ResponseEntity.noContent().build();
    }
}
