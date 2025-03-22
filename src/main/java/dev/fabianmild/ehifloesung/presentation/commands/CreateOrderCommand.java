package dev.fabianmild.ehifloesung.presentation.commands;

import dev.fabianmild.ehifloesung.domain.entities.Customer;
import dev.fabianmild.ehifloesung.domain.valueObjects.OrderLineItem;

import java.util.List;

public record CreateOrderCommand(String orderNumber, Customer customer, List<OrderLineItem> orderLineItems) {
}
