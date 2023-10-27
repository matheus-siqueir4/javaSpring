package macrobrang.dpvat.ServiceImplements;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import macrobrang.dpvat.Models.Vendedor;
import macrobrang.dpvat.Repository.VendedorRepository;
import macrobrang.dpvat.ServiceImplements.Exceptions.ObjectNotFoundException;
import macrobrang.dpvat.Services.VendedorService;

@Component
public class VendedorServiceImpl implements VendedorService {

    @Autowired
    VendedorRepository repository;

    @Override
    @Transactional
    public Vendedor createVendedor(Vendedor vendedor) {
        vendedor.setId(null);
        vendedor = this.repository.save(vendedor);
        return vendedor;
    }

    @Override
    @Transactional
    public Vendedor updateVendedor(Vendedor vendedor) {
        Vendedor newVendedor = this.findbyIdVendedor(vendedor.getId());
        newVendedor.setNome(vendedor.getNome());
        
        return this.repository.save(newVendedor);
    }

    @Override
    public void deleteVendedor(UUID id) {
        findbyIdVendedor(id);

        try {
            this.repository.deleteById(id);
        
        } catch (Exception ex) {
            throw new DataIntegrityViolationException(
                "Não foi possível excluir o vendedor pois há registros relacionados");
        } 
    }

    @Override
    public Vendedor findbyIdVendedor(UUID id) {
        Optional<Vendedor> vendedor = this.repository.findById(id);
        return vendedor.orElseThrow(() -> new ObjectNotFoundException(
            "Não foi possível encontrar o vendedor com este id:" + id
        ));
    }

    @Override
    public List<Vendedor> getAllVendedor() {
        return this.repository.findAll();
    }
}
