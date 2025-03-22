package dev.fabianmild.ehifloesung.domain.entities;

import dev.fabianmild.ehifloesung.domain.enums.InvoiceStatus;
import dev.fabianmild.ehifloesung.domain.valueObjects.InvoiceLineItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Builder
@Table(name = "invoices")
public class Invoice extends AbstractPersistable<Long> {
    @NotNull
    @NotBlank
    private String invoiceNumber;
    @NotNull
    private LocalDate issueDate;
    @NotNull
    private LocalDate dueDate;
    @NotNull
    private LocalDate settlementDate;
    private InvoiceStatus invoiceStatus;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "order", foreignKey = @ForeignKey(name = "fk_order_invoice"))
    private Order order;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "customer", foreignKey = @ForeignKey(name = "fk_order_customer"))
    private Customer customer;

    @ElementCollection
    @JoinTable(name = "invoiceLineItems", foreignKey = @ForeignKey(name = "fk_order_invoicelineitem"))
    private List<InvoiceLineItem> invoiceLineItems;
}
