package com.entrega.pre_entrega_2.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name="factura")
public class FacturaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne

    @JoinColumn(name = "clientes_id")
    private ClienteModel cliente;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    private double total;
}
