package com.entrega.pre_entrega_2.controller;


import com.entrega.pre_entrega_2.model.ProductoModel;
import com.entrega.pre_entrega_2.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping(path = "api/products")
@RestController
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        return new ResponseEntity<>(this.productoService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ProductoModel> create(@RequestBody ProductoModel producto){
        ProductoModel nuevoProducto = this.productoService.create(producto.getDescripcion(), producto.getCodigo(), producto.getStock(), producto.getPrecio());
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }
    /*
        EJEMPLO JSON
        {
            "descripcion": "Descripción del producto",
            "codigo": "codigo del producto",
            "stock": 10,
            "precio": 10.0
        }
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductoModel> update(@RequestBody ProductoModel productUpdate, @PathVariable Integer id){
        return new ResponseEntity<>(this.productoService.update(productUpdate, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            productoService.delete(id);
            return new ResponseEntity<>("Producto eliminado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el Producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}