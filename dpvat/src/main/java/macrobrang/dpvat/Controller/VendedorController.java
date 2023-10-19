package macrobrang.dpvat.Controller;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import macrobrang.dpvat.Exception.Response;
import macrobrang.dpvat.Models.Vendedor;
import macrobrang.dpvat.RepositoryImplements.VendedorRepositoryImpl;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/vendedor")
public class VendedorController {

    @Autowired
    private VendedorRepositoryImpl service;

    @PostMapping
    private ResponseEntity<Response<Vendedor>> save(@Valid @RequestBody Vendedor vendedor, BindingResult result) {
        return service.insert(vendedor, result);
    }

    @PutMapping
    private ResponseEntity<Response<Vendedor>> alterar(@Valid @RequestBody Vendedor vendedor, BindingResult result) {
        return service.insert(vendedor, result);
    }

    @GetMapping
    private List<Vendedor> getList() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Response<Vendedor>> buscarPorId(@PathVariable UUID id) {
        return service.findById(id);
    }

    @DeleteMapping
    private ResponseEntity<Response<Vendedor>> deletePorId(@PathVariable UUID id) {
        return service.deleteById(id);
    }
    
}

