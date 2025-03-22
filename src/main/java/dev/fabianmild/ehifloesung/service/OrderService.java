package dev.fabianmild.ehifloesung.service;

import dev.fabianmild.ehifloesung.domain.RevenueDerivationReport;
import dev.fabianmild.ehifloesung.domain.RevenueDerivationReportElement;
import dev.fabianmild.ehifloesung.domain.entities.Customer;
import dev.fabianmild.ehifloesung.domain.entities.Order;
import dev.fabianmild.ehifloesung.domain.enums.OrderStatus;
import dev.fabianmild.ehifloesung.domain.valueObjects.OrderLineItem;
import dev.fabianmild.ehifloesung.persistence.OrderRepository;
import dev.fabianmild.ehifloesung.presentation.commands.CreateOrderCommand;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public RevenueDerivationReport determineRevenueDerivation(Customer c, LocalDate start, LocalDate end) {
        var orders = orderRepository.findByCustomerAndPlacementDateBetween(c, start, end)
                .stream()
                .filter(order -> order.getOrderStatus() != OrderStatus.DELIVERED);

        return new RevenueDerivationReport(c, orders.flatMap(order ->
                        order.getOrderLineItems().stream().map(orderLineItem ->
                                    new RevenueDerivationReportElement(order,
                                            orderLineItem.getArticle(),
                                            orderLineItem.getArticle().getUnitPriceInCents() - orderLineItem.getUnitPriceInCents(),
                                            orderLineItem.getUnitPriceInCents()
                                            )
                        )).toList()
                );
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public Optional<Order> findOrderByNr(String orderNumber){
        return orderRepository.findAll().stream().filter(order -> order.getOrderNumber().equals(orderNumber)).findFirst();
    }

    public Order createOrder(String orderNumber, Customer customer, List<OrderLineItem> orderLineItems) {
        /*return orderRepository.save(new Order(...));*/
        //todo: implement
        return null;
    }

    public void patchOrder(Order order) {
        //todo: implement
    }
}
