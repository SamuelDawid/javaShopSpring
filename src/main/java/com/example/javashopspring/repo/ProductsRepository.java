package com.example.javashopspring.repo;

import com.example.javashopspring.domain.resources.Electronics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
//Repository

public interface ProductsRepository extends JpaRepository<Electronics, String> {
    @Transactional
    @Modifying
    @Query("update Electronics e set e.quantity = e.quantity - :qty " +
            "where e.id = :id and e.quantity >= :qty")
    int decreaseStock(@Param("id") String id, @Param("qty") int qty);
}
