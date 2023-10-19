package macrobrang.dpvat.RepositoryImplements;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import macrobrang.dpvat.Exception.Response;
import macrobrang.dpvat.Models.Vendedor;
import macrobrang.dpvat.Repository.VendedorRepository;
import macrobrang.dpvat.Services.VendedorService;

@Component
public class VendedorRepositoryImpl implements VendedorService {

    
    @Autowired
    private VendedorRepository repository;

    @Override
    public ResponseEntity<Response<Vendedor>> insert(@Valid Vendedor vendedor, BindingResult result) {
        
        var response = new Response<Vendedor>();
        response.setObj(vendedor);

        if (result.hasErrors()) {
            response.addValidationError(result);
            return ResponseEntity.badRequest().body(response);
        }

        repository.save(vendedor);
        return ResponseEntity.created(null).body(response);
    }

    @Override
    public List<Vendedor> getAll() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity<Response<Vendedor>> deleteById(UUID id) {
        var response = new Response<Vendedor>();
        
        Vendedor obj = null;

        try {
            repository.deleteById(id);
        
        } catch (Exception e) {
            response.getMapErrors().put("Resultado", "Não encontrado");
            return ResponseEntity.notFound().build();
        }

        response.setObj(obj);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response<Vendedor>> findById(UUID id) {
        var response = new Response<Vendedor>();
        
        Vendedor obj = null;

        try {
            obj = repository.findById(id).get();
        
        } catch (Exception e) {
            response.getMapErrors().put("Resultado", "Não encontrado");
            return ResponseEntity.notFound().build();
        }

        response.setObj(obj);
        return ResponseEntity.ok(response);
    }

    
    
}
