package com.entrega.pre_entrega_2.repository;

import com.entrega.pre_entrega_2.model.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoModel,Integer> {
}
