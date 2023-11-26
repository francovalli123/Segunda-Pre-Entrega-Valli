package com.entrega.pre_entrega_2.service;

import com.entrega.pre_entrega_2.model.ProductoModel;
import com.entrega.pre_entrega_2.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    //Get
    public String findById(Integer id){
        Optional<ProductoModel> cajaProducto = this.productoRepository.findById(id);
        if(cajaProducto.isPresent()){
            ProductoModel p = cajaProducto.get();

            String jsonFormat = " {\n" +
                    "                \"id\": " + p.getId() + "," +
                    "                \"descripción\": " + "\"" + p.getDescripcion() + "\"" + "," +
                    "                \"codigo\": " + "\""  + p.getCodigo() + "\""+ "," +
                    "                \"stock\": " + "\"" + p.getStock() + "\"" + "," +
                    "                \"precio\": " + "\"" + p.getPrecio() + "\"" +
                    "              }";

            return jsonFormat;
        }
        return null;
    }

    //Post
    public ProductoModel create(String descripcion, String codigo, int stock, double precio){
        ProductoModel p = new ProductoModel();
        p.setDescripcion(descripcion);
        p.setCodigo(codigo);
        p.setStock(stock);
        p.setPrecio(precio);
        return this.productoRepository.save(p);
    }

    //Put
    public ProductoModel update(ProductoModel producto, Integer id){
        Optional<ProductoModel> productoDB = this.productoRepository.findById(id);
        ProductoModel p;
        if(productoDB.isPresent()){
            p = productoDB.get();
            p.setDescripcion(producto.getDescripcion());
            p.setCodigo(producto.getCodigo());
            p.setStock(producto.getStock());
            p.setPrecio(producto.getPrecio());
            return this.productoRepository.save(p);
        }else{
            return null;
        }
    }

    //Delete
    public void delete(Integer id) {
        productoRepository.deleteById(id);
    }

}
