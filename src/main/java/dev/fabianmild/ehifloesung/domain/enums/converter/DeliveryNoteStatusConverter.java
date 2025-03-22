package dev.fabianmild.ehifloesung.domain.enums.converter;

import dev.fabianmild.ehifloesung.domain.enums.DeliveryNoteStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DeliveryNoteStatusConverter implements AttributeConverter<DeliveryNoteStatus,String> {
    @Override
    public String convertToDatabaseColumn(DeliveryNoteStatus deliveryNoteStatus) {
        return switch (deliveryNoteStatus) {
            case DEPOSITED -> "DEP";
            case SHIPPED -> "SHI";
            case DELIVERY -> "DEL";
            case PREPARED -> "PRE";
        };
    }

    @Override
    public DeliveryNoteStatus convertToEntityAttribute(String s) {
        return switch (s) {
            case "DEL" -> DeliveryNoteStatus.DELIVERY;
            case "SHI" -> DeliveryNoteStatus.SHIPPED;
            case "DEP" -> DeliveryNoteStatus.DEPOSITED;
            case "PRE" -> DeliveryNoteStatus.PREPARED;
            default -> throw new IllegalStateException("Unexpected value: " + s);
        };
    }
}
