package com.gabrieldeoliveira.cursospring.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabrieldeoliveira.cursospring.domain.Client;
import com.gabrieldeoliveira.cursospring.dto.ClientDTO;
import com.gabrieldeoliveira.cursospring.dto.NewClientDTO;
import com.gabrieldeoliveira.cursospring.services.ClientService;
import com.gabrieldeoliveira.cursospring.services.DtoConverter;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientResource {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> list() {
        List<Client> list = clientService.list();
        List<ClientDTO> listDto = list.stream().map(x -> DtoConverter.fromObj(x)).toList();
        return ResponseEntity.ok(listDto);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody NewClientDTO objDto) {
        Client newObj = objDto.toObj();
        newObj = clientService.insert(newObj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Integer id) {
        Client findedObj = clientService.findById(id);
        return ResponseEntity.ok(findedObj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ClientDTO objDto) {
        Client obj = objDto.toObj();
        obj.setId(id);
        obj = clientService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ClientDTO>> listPageable(Pageable pageable) {
        Page<Client> page = clientService.listPageable(pageable);
        Page<ClientDTO> pageDto = page.map(x -> DtoConverter.fromObj(x));
        return ResponseEntity.ok(pageDto);
    }
}
