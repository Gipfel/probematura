package dev.fabianmild.ehifloesung.domain.enums.converter;


import dev.fabianmild.ehifloesung.domain.enums.OrderStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus,String> {
    @Override
    public String convertToDatabaseColumn(OrderStatus orderStatus) {
        return switch (orderStatus) {
            case DELIVERED -> "DEL";
            case PLACED -> "PLA";
            case PARTIALLY_DELIVERED -> "PAR";
            case null -> throw new IllegalStateException("Unexpected value: NULL");
        };
    }

    @Override
    public OrderStatus convertToEntityAttribute(String s) {
        return switch (s) {
            case "DEL" -> OrderStatus.DELIVERED;
            case "PLA" -> OrderStatus.PLACED;
            case "PAR" -> OrderStatus.PARTIALLY_DELIVERED;
            default -> throw new IllegalStateException("Unexpected value: " + s);
        };
    }
}

