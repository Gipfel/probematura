package dev.fabianmild.ehifloesung.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "customers")
public class Customer extends AbstractPersistable<Long> {
    @NotNull
    @NotBlank
    @Column(unique = true)
    private String customerNumber;
    @NotNull
    @NotBlank
    private String name;
}
