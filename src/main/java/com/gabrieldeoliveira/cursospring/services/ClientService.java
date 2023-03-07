package com.gabrieldeoliveira.cursospring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gabrieldeoliveira.cursospring.domain.Client;
import com.gabrieldeoliveira.cursospring.repositories.ClientRepository;
import com.gabrieldeoliveira.cursospring.services.exceptions.DataIntegrityException;
import com.gabrieldeoliveira.cursospring.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
    
    @Autowired 
    private ClientRepository clientRepository;

    public List<Client> list() {
        return clientRepository.findAll();
    }

    public Page<Client> listPageable(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    public Client findById(Integer id) {
        Client obj = clientRepository.findById(id)
                        .orElseThrow(() -> new ObjectNotFoundException(
                            "Object not found ("+ Client.class.getSimpleName() +") Id: " + id
                        ));
        return obj;
    }
    
    public Client update(Client obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Error when updating category: object was null");
        }
        Client newObj = findById(obj.getId());
        updateData(newObj, obj);
        return clientRepository.save(newObj);
    }

    private void updateData(Client newObj, Client obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    public void deleteById(Integer id) {
        findById(id);
        try {
            clientRepository.deleteById(id);
        } catch(DataIntegrityViolationException e) {
            throw new DataIntegrityException("Can't delete client");
        }
    }
}
