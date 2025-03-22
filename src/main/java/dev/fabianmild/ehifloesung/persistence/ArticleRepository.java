package dev.fabianmild.ehifloesung.persistence;

import dev.fabianmild.ehifloesung.domain.entities.Article;
import dev.fabianmild.ehifloesung.domain.entities.DeliveryNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
