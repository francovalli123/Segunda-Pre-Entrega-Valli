package com.entrega.pre_entrega_2.controller;

import com.entrega.pre_entrega_2.model.ClienteModel;
import com.entrega.pre_entrega_2.model.FacturaModel;
import com.entrega.pre_entrega_2.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "api/clients")
@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/")
    public ResponseEntity<ClienteModel> create(@RequestBody ClienteModel cliente) {
        ClienteModel nuevoCliente = this.clienteService.create(cliente.getNombre(), cliente.getApellido(), cliente.getNumeroDocumento());
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }
    /*
        EJEMPLO JSON
        {
            "nombre": "nombre",
            "apellido": "apellido",
            "numeroDocumento": "123456789"
        }
     */

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(this.clienteService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ClienteModel>> getAllClientes() {
        List<ClienteModel> clientes = clienteService.getAllClientes();

        if (!clientes.isEmpty()) {
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClienteModel> update(@RequestBody ClienteModel clientUpdate, @PathVariable Integer id) {
        return new ResponseEntity<>(this.clienteService.update(clientUpdate, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            clienteService.delete(id);
            return new ResponseEntity<>("Cliente eliminado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el cliente: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}