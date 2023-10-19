package macrobrang.dpvat.Models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Vendedor {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "contato_id")
    private Contato contato;

    private LocalDateTime createdAt;

}
