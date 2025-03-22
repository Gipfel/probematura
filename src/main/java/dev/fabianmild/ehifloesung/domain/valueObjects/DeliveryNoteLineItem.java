package dev.fabianmild.ehifloesung.domain.valueObjects;

import dev.fabianmild.ehifloesung.domain.entities.Article;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Builder
public class DeliveryNoteLineItem {
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "article", foreignKey = @ForeignKey(name = "fk_deliverynotelineitem_article"))
    private Article article;
    @Column(unique = true)
    @NotNull
    @NotBlank
    private String articleNumber;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @PositiveOrZero
    private Long quantity;
    @NotNull
    @PositiveOrZero
    private Long priceInCents;
}
