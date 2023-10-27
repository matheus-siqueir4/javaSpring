package siqueir4.dpvat.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import siqueir4.dpvat.models.Vendedor;

@Service
public interface VendedorService {
    
    public Vendedor createVendedor(Vendedor vendedor);
    
    public Vendedor updateVendedor(Vendedor vendedor);

    public void deleteVendedor(UUID id);

    public Vendedor findbyIdVendedor(UUID id);

    public List<Vendedor> getAllVendedor(); 
}
