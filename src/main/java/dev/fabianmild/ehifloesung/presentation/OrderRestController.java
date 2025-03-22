package dev.fabianmild.ehifloesung.presentation;

import dev.fabianmild.ehifloesung.domain.entities.Order;
import dev.fabianmild.ehifloesung.presentation.commands.CreateOrderCommand;
import dev.fabianmild.ehifloesung.presentation.commands.PatchOrderCommand;
import dev.fabianmild.ehifloesung.presentation.dtos.OrderDto;
import dev.fabianmild.ehifloesung.service.OrderService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static dev.fabianmild.ehifloesung.presentation.RestAPIRouteSupport.BASE_URL;
import static dev.fabianmild.ehifloesung.presentation.RestAPIRouteSupport.SLASH_;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(BASE_URL + SLASH_ + OrderRestController.ORDER_ROUTE)
public class OrderRestController {

    public static final String ORDER_ROUTE = "orders";

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAll().stream().map(OrderDto::new).toList());
    }

    @GetMapping(SLASH_ + "{orderNumber}")
    public ResponseEntity<OrderDto> getOrderByNumber(@PathVariable String orderNumber){
        return orderService.findOrderByNr(orderNumber)
                .map(OrderDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EntityModel<OrderDto>> createOrder(@RequestBody CreateOrderCommand cmd) {
        var order = orderService.createOrder(cmd.orderNumber(), cmd.customer(), cmd.orderLineItems());

        // HATEOAS
        var base = linkTo(methodOn(OrderRestController.class).getOrderByNumber(order.getOrderNumber()));

        // HATEOAS
        EntityModel<OrderDto> invoiceResource = EntityModel.of(new OrderDto(order),
                base.withSelfRel());

        return ResponseEntity.created(base.toUri()).body(invoiceResource);
    }


    @PatchMapping(SLASH_ + "{orderNumber}")
    public ResponseEntity<Void> patchOrder(@PathVariable String orderNumber, @RequestBody PatchOrderCommand cmd) {
        var order = orderService.findOrderByNr(orderNumber);
        if (order.isPresent()) {
            orderService.patchOrder(order.get());
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
