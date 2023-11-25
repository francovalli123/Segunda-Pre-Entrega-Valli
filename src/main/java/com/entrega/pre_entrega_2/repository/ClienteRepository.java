package com.entrega.pre_entrega_2.repository;

import com.entrega.pre_entrega_2.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel,Integer> {
}