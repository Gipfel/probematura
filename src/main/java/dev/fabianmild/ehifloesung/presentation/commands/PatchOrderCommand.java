package dev.fabianmild.ehifloesung.presentation.commands;

import dev.fabianmild.ehifloesung.domain.entities.Customer;
import dev.fabianmild.ehifloesung.domain.valueObjects.OrderLineItem;

import java.util.List;

public record PatchOrderCommand(Customer customer, List<OrderLineItem> orderLineItems) {
}
