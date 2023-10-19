package macrobrang.dpvat.Services;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import macrobrang.dpvat.Exception.Response;
import macrobrang.dpvat.Models.Vendedor;

@Service
public interface VendedorService {
    
    public ResponseEntity<Response<Vendedor>> insert(@Valid @RequestBody Vendedor vendedor, BindingResult result);

    public List<Vendedor> getAll();

    public ResponseEntity<Response<Vendedor>> deleteById(UUID id);

    public ResponseEntity<Response<Vendedor>> findById(UUID id);
}
