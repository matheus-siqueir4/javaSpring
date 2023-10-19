package macrobrang.dpvat.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import macrobrang.dpvat.Models.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, UUID> {

}
