package ru.ilezzov.business.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ilezzov.business.core.entity.product.Category;
import ru.ilezzov.business.core.entity.product.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(Category category);

    List<Product> findAllByStatus(Product.Status status);
}
