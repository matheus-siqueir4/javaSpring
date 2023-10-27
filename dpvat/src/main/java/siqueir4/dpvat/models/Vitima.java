package siqueir4.dpvat.models;

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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import siqueir4.dpvat.constants.Acao;
import siqueir4.dpvat.constants.Regiao;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Vitima extends BaseEntity {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @NotBlank
    @Column(length = 100)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataDeNascimento;

    @NotNull
    @Column(nullable = false)
    private boolean obito;

    @NotNull
    @Column(nullable = false, length = 20)
    private Acao acao;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String descricao;

    @NotNull
    @Column(nullable = false, length = 20)
    private Regiao regiao;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "vitima_id", nullable = false)
    private List<Contato> contato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;
}
