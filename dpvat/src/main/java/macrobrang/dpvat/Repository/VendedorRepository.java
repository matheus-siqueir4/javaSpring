package macrobrang.dpvat.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import macrobrang.dpvat.Models.Vendedor;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, UUID>{
    
}
