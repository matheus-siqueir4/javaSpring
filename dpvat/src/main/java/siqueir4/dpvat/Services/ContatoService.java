package siqueir4.dpvat.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import siqueir4.dpvat.models.Contato;

@Service
public interface ContatoService {
    
    public Contato createContato(Contato contato);

    public Contato updateContato(Contato contato);

    public void deleteContato(UUID id);

    public Contato findByIdContato(UUID id);

    public List<Contato> getAllContato();
}
