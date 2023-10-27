package siqueir4.dpvat.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import siqueir4.dpvat.models.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, UUID> {

}
