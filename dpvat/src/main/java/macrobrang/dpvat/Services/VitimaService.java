package macrobrang.dpvat.Services;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import macrobrang.dpvat.Models.Vitima;
import macrobrang.dpvat.Exception.Response;

@Service
public interface VitimaService {
    
    public ResponseEntity<Response<Vitima>> insert(@Valid @RequestBody Vitima vitima, BindingResult result);

    public List<Vitima> getAll();

    public ResponseEntity<Response<Vitima>> deleteById(UUID id);

    public ResponseEntity<Response<Vitima>> findById(UUID id);

}
