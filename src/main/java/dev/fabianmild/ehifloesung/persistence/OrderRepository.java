package dev.fabianmild.ehifloesung.persistence;

import dev.fabianmild.ehifloesung.domain.entities.Customer;
import dev.fabianmild.ehifloesung.domain.entities.Order;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerAndPlacementDateBetween(@NotNull Customer customer, @NotNull LocalDate start, @NotNull LocalDate end);
}
