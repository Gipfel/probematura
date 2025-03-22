package dev.fabianmild.ehifloesung.persistence;

import dev.fabianmild.ehifloesung.domain.entities.DeliveryNote;
import dev.fabianmild.ehifloesung.domain.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryNoteRepository extends JpaRepository<DeliveryNote, Long> {
}
