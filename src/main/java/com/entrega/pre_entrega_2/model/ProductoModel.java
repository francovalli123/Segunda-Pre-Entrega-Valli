package com.entrega.pre_entrega_2.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="productos")
public class ProductoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descripcion;
    private String codigo;
    private int stock;
    private double precio;

}
