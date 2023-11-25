package com.entrega.pre_entrega_2.service;

import com.entrega.pre_entrega_2.model.ClienteModel;
import com.entrega.pre_entrega_2.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public ClienteModel create(String nombre, String apellido, String numeroDocumento){
        ClienteModel c = new ClienteModel();
        c.setNombre(nombre);
        c.setApellido(apellido);
        c.setNumeroDocumento(numeroDocumento);
        return this.clienteRepository.save(c);
    }

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

}
