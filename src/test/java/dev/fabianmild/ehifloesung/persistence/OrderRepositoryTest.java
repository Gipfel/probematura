package dev.fabianmild.ehifloesung.persistence;

import dev.fabianmild.ehifloesung.domain.entities.Article;
import dev.fabianmild.ehifloesung.domain.entities.Customer;
import dev.fabianmild.ehifloesung.domain.entities.Order;
import dev.fabianmild.ehifloesung.domain.enums.OrderStatus;
import dev.fabianmild.ehifloesung.domain.valueObjects.OrderLineItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void ensureSaveAndReadWorks(){
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

        var saved = orderRepository.save(order);

        assertThat(saved).isNotNull();
        assertThat(saved).isSameAs(order);
        assertThat(saved.getId()).isNotNull();
    }

}