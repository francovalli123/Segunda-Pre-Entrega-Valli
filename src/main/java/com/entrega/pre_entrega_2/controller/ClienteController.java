package com.entrega.pre_entrega_2.controller;

import com.entrega.pre_entrega_2.model.ClienteModel;
import com.entrega.pre_entrega_2.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "api/clients")
@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/")
    public ResponseEntity<ClienteModel> create(@RequestBody ClienteModel cliente){
        ClienteModel nuevoCliente = this.clienteService.create(cliente.getNombre(), cliente.getApellido(), cliente.getNumeroDocumento());
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        return new ResponseEntity<>(this.clienteService.findById(id), HttpStatus.OK);
    }
}
