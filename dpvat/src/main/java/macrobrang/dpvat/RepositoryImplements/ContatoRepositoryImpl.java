package macrobrang.dpvat.RepositoryImplements;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import macrobrang.dpvat.Models.Contato;
import macrobrang.dpvat.Repository.ContatoRepository;
import macrobrang.dpvat.Services.ContatoService;

@Component
public class ContatoRepositoryImpl implements ContatoService {

    @Autowired
    private ContatoRepository repository;

    @Transactional
    @Override
    public Contato createContato(Contato contato) {
        contato.setId(null); // Define o objeto como null;
        contato = this.repository.save(contato);
        return contato;
    
    }

    @Transactional
    @Override
    public Contato updateContato(Contato contato) {
        Contato newContato = findByIdContato(contato.getId());
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
            throw new RuntimeException(
                "Não é possível excluir o contato"
            );
        }
    }

    @Override
    public Contato findByIdContato(UUID id) {
        Optional<Contato> contato = this.repository.findById(id);
        return contato.orElseThrow(() -> new RuntimeException(
            "Usuário não encontrado com o id: " + id
        ));
    }

    @Override
    public List<Contato> getAllContato() {
        return this.repository.findAll();
    }

}
