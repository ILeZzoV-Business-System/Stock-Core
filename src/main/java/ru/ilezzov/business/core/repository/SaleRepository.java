package ru.ilezzov.business.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ilezzov.business.core.entity.sale.Sale;

import java.time.Instant;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findAllByCreatedAtBetween(Instant createdAtAfter, Instant createdAtBefore);

    List<Sale> findAllByUserId(Long userId);

    List<Sale> findAllByProductId(Long productId);
}
