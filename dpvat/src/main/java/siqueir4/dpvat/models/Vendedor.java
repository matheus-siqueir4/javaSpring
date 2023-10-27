package siqueir4.dpvat.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Vendedor extends BaseEntity {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nome;

}
