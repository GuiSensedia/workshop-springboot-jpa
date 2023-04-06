package com.educandoweb.course.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.model.domain.OrderItemDomain;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepository extends JpaRepository<OrderItemDomain, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM OrderItemDomain OI WHERE OI.id.product.id = :productId")
    void deleteByProductId(Long productId);

}
