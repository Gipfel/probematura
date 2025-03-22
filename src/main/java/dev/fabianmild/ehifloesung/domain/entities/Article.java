package dev.fabianmild.ehifloesung.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "articles")
public class Article extends AbstractPersistable<Long> {
    @NotNull
    @NotBlank
    @Column(unique = true)
    //Falls man ein @Column auf ein Embedded setzen möchte:
    //@AttributeOverrides({
    //        @AttributeOverride(name = "articleNumber", column = @Column(name = "namestattvalue"))
    //})
    private String articleNumber;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @PositiveOrZero
    private Long unitPriceInCents;
    @NotNull
    @PositiveOrZero
    private Long itemsInStock;
}
