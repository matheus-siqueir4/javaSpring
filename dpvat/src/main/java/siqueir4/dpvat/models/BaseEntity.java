package siqueir4.dpvat.models;

import java.time.LocalDateTime;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import lombok.Getter;

@MappedSuperclass
@Getter
public class BaseEntity {
    
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    @PrePersist
    public void createdAtPersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void updateAtPersist() {
        this.updateAt = LocalDateTime.now();
    }

}
