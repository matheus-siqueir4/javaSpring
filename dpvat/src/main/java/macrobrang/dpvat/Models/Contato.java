package macrobrang.dpvat.Models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import macrobrang.dpvat.validation.constraints.NumeroCelularConstraint;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor 
public class Contato extends BaseEntity {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @NumeroCelularConstraint
    @Column(unique = true, nullable = false)
    private String numeroCelular;

    @Email
    @NotBlank
    @Column(unique = true, nullable = true)
    private String email;

}
