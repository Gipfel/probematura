package dev.fabianmild.ehifloesung.service;

import dev.fabianmild.ehifloesung.domain.RevenueDerivationReport;
import dev.fabianmild.ehifloesung.domain.RevenueDerivationReportElement;
import dev.fabianmild.ehifloesung.domain.entities.Article;
import dev.fabianmild.ehifloesung.domain.entities.Customer;
import dev.fabianmild.ehifloesung.domain.entities.Order;
import dev.fabianmild.ehifloesung.domain.enums.OrderStatus;
import dev.fabianmild.ehifloesung.domain.valueObjects.OrderLineItem;
import dev.fabianmild.ehifloesung.persistence.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testDetermineRevenueDerivation() {
        var a = Article.builder()
                .articleNumber("nr")
                .itemsInStock(120L)
                .description("dr")
                .name("name")
                .unitPriceInCents(10L).build();
        var ol = OrderLineItem.builder()
                .name("name")
                .article(a)
                .articleNumber(a.getArticleNumber())
                .description("dr")
                .unitPriceInCents(12L)
                .quantity(4L)
                .build();
        var c = Customer.builder()
                .customerNumber("nr")
                .name("name")
                .build();
        var order = Order.builder()
                .orderNumber("HI")
                .orderLineItems(List.of(ol))
                .orderStatus(OrderStatus.PLACED)
                .placementDate(LocalDate.now())
                .customer(c)
                .build();

        var rep = new RevenueDerivationReport(c, List.of(new RevenueDerivationReportElement(order, a,-2L,12L)));

        when(orderRepository.findByCustomerAndPlacementDateBetween(any(), any(), any())).thenReturn(List.of(order));

        var report = orderService.determineRevenueDerivation(c, LocalDate.now(), LocalDate.now().plusMonths(5));

        assertThat(report).isEqualTo(rep);
    }
}