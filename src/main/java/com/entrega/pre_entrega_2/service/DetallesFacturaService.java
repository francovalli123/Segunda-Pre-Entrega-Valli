package com.entrega.pre_entrega_2.service;

import com.entrega.pre_entrega_2.model.DetallesFacturaModel;
import com.entrega.pre_entrega_2.model.FacturaModel;
import com.entrega.pre_entrega_2.model.ProductoModel;
import com.entrega.pre_entrega_2.repository.DetallesFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallesFacturaService {

    @Autowired
    private DetallesFacturaRepository detallesFacturaRepository;

    @Autowired
    private ProductoService productoService;

    public DetallesFacturaModel create(FacturaModel factura, int cantidadProductos, double importe) {
        DetallesFacturaModel detallesFactura = new DetallesFacturaModel();
        detallesFactura.setFactura(factura);

        ProductoModel producto = new ProductoModel();
        producto.setId(1);
        detallesFactura.setProducto(producto);

        detallesFactura.setCantidadProductos(cantidadProductos);
        detallesFactura.setImporte(importe);
        factura.setTotal(factura.getTotal() + importe);

        detallesFacturaRepository.save(detallesFactura);

        return detallesFactura;
    }

    public DetallesFacturaModel findById(Integer id) {
        return detallesFacturaRepository.findById(id).orElse(null);
    }

    public List<DetallesFacturaModel> getAllFacturasDetalles() {
        return detallesFacturaRepository.findAll();
    }
}