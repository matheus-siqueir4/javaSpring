package macrobrang.dpvat.Models;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import macrobrang.dpvat.Constants.Acao;
import macrobrang.dpvat.Constants.Regiao;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Vitima extends BaseEntity {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @NotBlank
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataDeNascimento;

    @NotBlank
    @Column(nullable = false)
    private boolean obito;

    @NotBlank
    private Acao acao;

    @Column(nullable = true)
    private String descricao;

    @NotBlank
    @Column(nullable = false)
    private Regiao regiao;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "vitima_id")
    private List<Contato> contato;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;

}
