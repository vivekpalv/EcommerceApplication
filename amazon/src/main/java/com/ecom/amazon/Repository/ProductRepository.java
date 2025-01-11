package com.ecom.amazon.Repository;

import com.ecom.amazon.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
    SELECT DISTINCT p FROM Product p LEFT JOIN p.attributes a LEFT JOIN a.fields f
    WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :searchString, '%'))
    OR LOWER(f.fieldKey) LIKE LOWER(CONCAT('%', :searchString, '%'))
    OR LOWER(f.fieldValue) LIKE LOWER(CONCAT('%', :searchString, '%'))
    """)
    Page<Product> filteredProduct(String searchString, Pageable pageable);

    @Query("""
    SELECT p FROM Product p JOIN p.attributes a WHERE p.id = :productId AND a.id = :attributeId
    """)
    Optional<Product> findProductByProductIdAndAttributeId(Long productId, Long attributeId);
}
