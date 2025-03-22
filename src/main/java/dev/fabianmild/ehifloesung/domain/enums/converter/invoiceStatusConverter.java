package dev.fabianmild.ehifloesung.domain.enums.converter;

import dev.fabianmild.ehifloesung.domain.enums.InvoiceStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class invoiceStatusConverter implements AttributeConverter<InvoiceStatus,String> {
    @Override
    public String convertToDatabaseColumn(InvoiceStatus invoiceStatus) {
        return switch (invoiceStatus) {
            case FULL -> "FUL";
            case OPEN -> "OPE";
            case PARTIALLY -> "PAR";
            case null -> throw new IllegalStateException("Unexpected value: NULL");
        };
    }

    @Override
    public InvoiceStatus convertToEntityAttribute(String s) {
        return switch (s) {
            case "FUL" -> InvoiceStatus.FULL;
            case "OPE" -> InvoiceStatus.OPEN;
            case "PAR" -> InvoiceStatus.PARTIALLY;
            default -> throw new IllegalStateException("Unexpected value: " + s);
        };
    }
}
