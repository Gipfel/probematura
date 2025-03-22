package dev.fabianmild.ehifloesung.presentation.dtos;

import dev.fabianmild.ehifloesung.domain.entities.Customer;
import dev.fabianmild.ehifloesung.domain.entities.Order;
import dev.fabianmild.ehifloesung.domain.enums.OrderStatus;
import dev.fabianmild.ehifloesung.domain.valueObjects.OrderLineItem;

import java.time.LocalDate;
import java.util.List;

public record OrderDto(String orderNumber, LocalDate placementDate, OrderStatus orderStatus, Customer customer, List<OrderLineItem> orderLineItems) {

    public OrderDto(Order order) {
        this(order.getOrderNumber(), order.getPlacementDate(), order.getOrderStatus(), order.getCustomer(), order.getOrderLineItems());
    }
}
