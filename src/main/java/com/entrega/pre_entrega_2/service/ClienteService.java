package com.entrega.pre_entrega_2.service;

import com.entrega.pre_entrega_2.model.ClienteModel;
import com.entrega.pre_entrega_2.model.FacturaModel;
import com.entrega.pre_entrega_2.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    //Post
    public ClienteModel create(String nombre, String apellido, String numeroDocumento){
        ClienteModel c = new ClienteModel();
        c.setNombre(nombre);
        c.setApellido(apellido);
        c.setNumeroDocumento(numeroDocumento);
        return this.clienteRepository.save(c);
    }

    //Get
    public String findById(Integer id){
        Optional<ClienteModel> cajaCliente = this.clienteRepository.findById(id);
        if(cajaCliente.isPresent()){
            ClienteModel c = cajaCliente.get();

            String jsonFormat = " {\n" +
                    "                \"id\": " + c.getId() + "," +
                    "                \"nombre\": " + "\"" + c.getNombre() + "\"" + "," +
                    "                \"apellido\": " + "\""  + c.getApellido() + "\""+ "," +
                    "                \"numeroDocumento\": " + "\"" + c.getNumeroDocumento() + "\"" +
                    "              }";

            return jsonFormat;
        }
        return null;
    }

    public List<ClienteModel> getAllClientes() {
        return clienteRepository.findAll();
    }

    //Put
    public ClienteModel update(ClienteModel client, Integer id){
        Optional<ClienteModel> clienteDB = this.clienteRepository.findById(id);
        ClienteModel c;
        if(clienteDB.isPresent()){
            c = clienteDB.get();
            c.setNombre(client.getNombre());
            c.setApellido(client.getApellido());
            c.setNumeroDocumento(client.getNumeroDocumento());
            return this.clienteRepository.save(c);
        }else{
            return null;
        }
    }

    //Delete
    public void delete(Integer id){
        this.clienteRepository.deleteById(id);
    }

}
