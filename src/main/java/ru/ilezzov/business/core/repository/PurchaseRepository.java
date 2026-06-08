package ru.ilezzov.business.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ilezzov.business.core.entity.purchses.Purchase;

import java.time.Instant;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByCreatedAtBetween(Instant createdAtAfter, Instant createdAtBefore);

    List<Purchase> findAllByProductId(Long productId);
}
