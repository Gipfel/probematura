package dev.fabianmild.ehifloesung.domain.entities;

import dev.fabianmild.ehifloesung.domain.enums.DeliveryNoteStatus;
import dev.fabianmild.ehifloesung.domain.enums.InvoiceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.aspectj.lang.annotation.After;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "deliverynotes")
public class DeliveryNote extends AbstractPersistable<Long> {
    @NotNull
    @NotBlank
    @Column(unique = true)
    private String deliveryNoteNumber;
    @NotNull
    private LocalDate issueDate;
    private DeliveryNoteStatus deliveryNoteStatus;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "invoice", foreignKey = @ForeignKey(name = "fk_invoice_delivery"))
    private Invoice invoice;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "order", foreignKey = @ForeignKey(name = "fk_order_delivery"))
    private Order order;
}
