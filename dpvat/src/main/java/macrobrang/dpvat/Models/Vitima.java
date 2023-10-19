package macrobrang.dpvat.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import macrobrang.dpvat.Constants.Acao;
import macrobrang.dpvat.Constants.Regiao;

@Entity
@Data
public class Vitima {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String nome;

    private LocalDate dataDeNascimento;

    private boolean obito;

    private Acao acao;

    private String descricao;

    private Regiao regiao;

    @ManyToOne
    @JoinColumn(name = "contato_id")
    private Contato contato;

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

    private LocalDateTime createdAt;


}
