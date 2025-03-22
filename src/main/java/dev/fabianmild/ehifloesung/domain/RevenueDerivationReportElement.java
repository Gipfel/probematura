package dev.fabianmild.ehifloesung.domain;

import dev.fabianmild.ehifloesung.domain.entities.Article;
import dev.fabianmild.ehifloesung.domain.entities.Order;

public record RevenueDerivationReportElement(Order order,
                                             Article article,
                                             Long revenueDifferenceInCents,
                                             Long currentPriceInCents) { }
