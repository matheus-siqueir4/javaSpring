package siqueir4.dpvat.ServiceImplements;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import siqueir4.dpvat.Repository.ContatoRepository;
import siqueir4.dpvat.ServiceImplements.Exceptions.DeletionNotAllowedException;
import siqueir4.dpvat.ServiceImplements.Exceptions.ObjectNotFoundException;
import siqueir4.dpvat.Services.ContatoService;
import siqueir4.dpvat.models.Contato;

@Service
public class ContatoServiceImpl implements ContatoService {

    @Autowired
    private ContatoRepository repository;

    @Transactional
    @Override
    public Contato createContato(Contato contato) {
        contato.setId(null);
        contato = this.repository.save(contato);
        return contato;
    
    }

    @Transactional
    @Override
    public Contato updateContato(Contato contato) {
        Contato newContato = this.findByIdContato(contato.getId());
        newContato.setNumeroCelular(contato.getNumeroCelular());
        newContato.setEmail(contato.getEmail());

        return this.repository.save(newContato);
        
    } 

    @Override
    public void deleteContato(UUID id) {
        findByIdContato(id);
        try {
            this.repository.deleteById(id);
        
        } catch (Exception e) {
            throw new DeletionNotAllowedException(
                "Não é possível excluir o contato, pois há registros relacionados"
            );
        }
    }

    @Override
    public Contato findByIdContato(UUID id) {
        Optional<Contato> contato = this.repository.findById(id);
        return contato.orElseThrow(() -> new ObjectNotFoundException(
            "Usuário não encontrado com o id: " + id
        ));
    }

    @Override
    public List<Contato> getAllContato() {
        return this.repository.findAll();
    }

}
