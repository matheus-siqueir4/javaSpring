package macrobrang.dpvat.ServiceImplements;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import macrobrang.dpvat.Models.Vitima;
import macrobrang.dpvat.Repository.VitimiaRepository;
import macrobrang.dpvat.ServiceImplements.Exceptions.DeletionNotAllowedException;
import macrobrang.dpvat.ServiceImplements.Exceptions.ObjectNotFoundException;
import macrobrang.dpvat.Services.VitimaService;

@Service
public class VitimaServiceImpl implements VitimaService {

    @Autowired
    VitimiaRepository repository;

    @Override
    @Transactional
    public Vitima createVitima(Vitima vitima) {
        vitima.setId(null);
        var vendedor = this.repository.save(vitima);
        return vendedor;
    }

    @Override
    @Transactional
    public Vitima updateVitima(Vitima vitima) {
        Vitima newVitima = this.findByIdVitima(vitima.getId());
        newVitima.setNome(vitima.getNome());
        newVitima.setObito(vitima.isObito());
        newVitima.setAcao(vitima.getAcao());
        newVitima.setDescricao(vitima.getDescricao());
        newVitima.setVendedor(vitima.getVendedor());
        newVitima.setRegiao(vitima.getRegiao());
        newVitima.setDataDeNascimento(vitima.getDataDeNascimento());
        newVitima.setContato(vitima.getContato());

        return this.repository.save(newVitima);

    }

    @Override
    public Vitima findByIdVitima(UUID id) {
        Optional<Vitima> vitima = this.repository.findById(id);
        return vitima.orElseThrow(() -> new ObjectNotFoundException(
            "Não foi possível encontrar a vítima de id " + id
        ));
    }

    @Override
    public void deleteVitima(UUID id) {
        this.findByIdVitima(id);

        try {
            this.repository.deleteById(id);
        
        } catch (Exception e) {
            throw new DeletionNotAllowedException(
            "Não é possível excluir o contato, pois há registros relacionados");
        }
    }

    @Override
    public List<Vitima> getAllVitima() {
        return this.repository.findAll();
    }
    
}
