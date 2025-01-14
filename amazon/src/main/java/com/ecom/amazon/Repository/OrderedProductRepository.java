package com.ecom.amazon.Repository;

import com.ecom.amazon.Entity.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Long> {

    List<OrderedProduct> findByVendorId(Long vendorId);
}
