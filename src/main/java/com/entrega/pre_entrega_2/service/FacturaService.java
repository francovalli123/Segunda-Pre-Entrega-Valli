package com.entrega.pre_entrega_2.service;

import com.entrega.pre_entrega_2.model.ClienteModel;
import com.entrega.pre_entrega_2.model.FacturaModel;
import com.entrega.pre_entrega_2.model.ProductoModel;
import com.entrega.pre_entrega_2.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private DetallesFacturaService detallesFacturaService;

    public FacturaModel create(ClienteModel cliente, double total) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no puede ser nulo al crear una factura.");
        }

        FacturaModel factura = new FacturaModel();
        factura.setCliente(cliente);
        factura.setFechaCreacion(new Date());
        factura.setTotal(total);

        try {
            FacturaModel nuevaFactura = facturaRepository.save(factura);

            // Crea automáticamente el detalle de la factura
            detallesFacturaService.create(nuevaFactura, 1, total);

            return nuevaFactura;
        } catch (Exception e) {
            // Manejar excepciones al guardar la factura en la base de datos
            throw new RuntimeException("Error al crear la factura: " + e.getMessage(), e);
        }
    }

    public FacturaModel findById(Integer id) {
        return facturaRepository.findById(id).orElse(null);
    }

    public List<FacturaModel> getAllFacturas() {
        return facturaRepository.findAll();
    }
    public FacturaModel update(FacturaModel factura, Integer id){
        Optional<FacturaModel> facturaDB = this.facturaRepository.findById(id);
        FacturaModel f;
        if(facturaDB.isPresent()){
            f = facturaDB.get();
            f.setCliente(factura.getCliente());
            f.setFechaCreacion(factura.getFechaCreacion());
            f.setTotal(factura.getTotal());
            return this.facturaRepository.save(f);
        }else{
            return null;
        }
    }

    public void delete(Integer id){
        this.facturaRepository.deleteById(id);
    }
}