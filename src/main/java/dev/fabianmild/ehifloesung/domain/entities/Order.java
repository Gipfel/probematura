package dev.fabianmild.ehifloesung.domain.entities;

import dev.fabianmild.ehifloesung.domain.enums.OrderStatus;
import dev.fabianmild.ehifloesung.domain.valueObjects.OrderLineItem;
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
@Table(name = "orders")
public class Order extends AbstractPersistable<Long> {
    @NotNull
    @NotBlank
    @Column(unique = true)
    private String orderNumber;
    @NotNull
    private LocalDate placementDate;
    @NotNull
    private OrderStatus orderStatus;
    @NotNull

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "customer", foreignKey = @ForeignKey(name = "fk_order_customer"))
    private Customer customer;

    @ElementCollection
    @JoinTable(name = "orderlineitems", foreignKey = @ForeignKey(name = "fk_order_orderlineitems"))
    private List<OrderLineItem> orderLineItems;
}
