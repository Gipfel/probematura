package dev.fabianmild.ehifloesung.domain;

import dev.fabianmild.ehifloesung.domain.entities.Customer;

import java.util.List;

public record RevenueDerivationReport(Customer customer,
                                      List<RevenueDerivationReportElement> elements) { }
