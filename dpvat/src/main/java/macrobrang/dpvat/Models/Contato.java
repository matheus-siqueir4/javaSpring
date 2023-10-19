package macrobrang.dpvat.Models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Contato {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @NotBlank
    @Size(min = 11, max = 11)
    @Column(unique = true, length = 11)
    private String numeroCelular;

    @Email
    @NotBlank
    private String email;

    private LocalDateTime createdAt;
}
