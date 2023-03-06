package com.gabrieldeoliveira.cursospring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrieldeoliveira.cursospring.domain.Client;
import com.gabrieldeoliveira.cursospring.repositories.ClientRepository;
import com.gabrieldeoliveira.cursospring.services.exceptions.ObjectNotFountException;

@Service
public class ClientService {
    
    @Autowired 
    private ClientRepository clientRepository;

    public Client findById(Integer id) {
        Client obj = clientRepository.findById(id)
                        .orElseThrow(() -> new ObjectNotFountException(
                            "Object not found ("+ Client.class.getSimpleName() +") Id: " + id
                        ));
        return obj;
    }
}
