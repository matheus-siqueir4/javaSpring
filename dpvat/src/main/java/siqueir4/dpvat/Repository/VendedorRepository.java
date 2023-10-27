package siqueir4.dpvat.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import siqueir4.dpvat.models.Vendedor;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, UUID>{
    
}
