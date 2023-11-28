package com.entrega.pre_entrega_2.controller;


import com.entrega.pre_entrega_2.model.ClienteModel;
import com.entrega.pre_entrega_2.model.DetallesFacturaModel;
import com.entrega.pre_entrega_2.model.FacturaModel;
import com.entrega.pre_entrega_2.model.ProductoModel;
import com.entrega.pre_entrega_2.service.DetallesFacturaService;
import com.entrega.pre_entrega_2.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "api/facturasDetalles")
@RestController
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private DetallesFacturaService detallesFacturaService;

    //Factura
    @PostMapping("/")
    public ResponseEntity<FacturaModel> create(@RequestBody ClienteModel cliente, @RequestParam double total) {
        FacturaModel nuevaFactura = this.facturaService.create(cliente, total);
        return new ResponseEntity<>(nuevaFactura, HttpStatus.CREATED);
    }
    /* EJEMPLO DEL JSON PARA EL POST

            {
                "id": 1,
                "nombre": "Nombre del cliente",
                "apellido": "Apellido del cliente",
                "numeroDocumento": "123456789"
                }
        Y luego pasar por parametro el total

     */

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        FacturaModel factura = this.facturaService.findById(id);
        if (factura != null) {
            return new ResponseEntity<>(factura, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Factura no encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<FacturaModel>> getAllFacturas() {
        List<FacturaModel> facturas = facturaService.getAllFacturas();

        if (!facturas.isEmpty()) {
            return new ResponseEntity<>(facturas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacturaModel> update(@RequestBody FacturaModel facturaUpdate, @PathVariable Integer id){
        return new ResponseEntity<>(this.facturaService.update(facturaUpdate, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            facturaService.delete(id);
            return new ResponseEntity<>("Factura eliminada correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la factura: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //DetallesFactura
    @PostMapping("/{facturaId}/detalles")
    public ResponseEntity<?> createDetalles(@PathVariable Integer facturaId,@RequestBody ProductoModel producto,@RequestParam int cantidadProductos,@RequestParam double importe) {
        FacturaModel factura = this.facturaService.findById(facturaId);

        if (factura != null) {
            DetallesFacturaModel nuevosDetalles = this.detallesFacturaService.create(factura, cantidadProductos, importe);
            return new ResponseEntity<>(nuevosDetalles, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Factura no encontrada", HttpStatus.NOT_FOUND);
        }
    }
    /*
        EJEMPLO DEL JSON PARA EL POST DE DETALLES

        {
            "id": 1,
            "descripcion": "Descripción del producto",
            "codigo": "ABC123",
            "stock": 10,
            "precio": 20.0
        }
        Luego se pasa por parametros la cantidadProductos y el importe
     */
}
