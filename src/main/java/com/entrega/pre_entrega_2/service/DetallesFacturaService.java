package com.entrega.pre_entrega_2.service;

import com.entrega.pre_entrega_2.model.DetallesFacturaModel;
import com.entrega.pre_entrega_2.model.FacturaModel;
import com.entrega.pre_entrega_2.model.ProductoModel;
import com.entrega.pre_entrega_2.repository.DetallesFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetallesFacturaService {

    @Autowired
    private DetallesFacturaRepository detallesFacturaRepository;

    @Autowired
    private ProductoService productoService;

    public DetallesFacturaModel create(FacturaModel factura, int cantidadProductos, double importe) {
        DetallesFacturaModel detallesFactura = new DetallesFacturaModel();
        detallesFactura.setFactura(factura);

        // Supongamos que obtenemos un producto por defecto, puedes ajustar esto según tus necesidades
        ProductoModel producto = new ProductoModel();
        producto.setId(1); // Supongamos que el producto con ID 1 ya existe en la base de datos
        detallesFactura.setProducto(producto);

        detallesFactura.setCantidadProductos(cantidadProductos);
        detallesFactura.setImporte(importe);

        // Actualizar el total de la factura
        factura.setTotal(factura.getTotal() + importe);

        // Guardar cambios en la factura y detalles de factura
        detallesFacturaRepository.save(detallesFactura);

        return detallesFactura;
    }

    public DetallesFacturaModel findById(Integer id) {
        return detallesFacturaRepository.findById(id).orElse(null);
    }
}