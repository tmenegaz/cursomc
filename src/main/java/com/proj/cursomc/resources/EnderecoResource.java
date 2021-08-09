package com.proj.cursomc.resources;

import com.proj.cursomc.domain.Endereco;
import com.proj.cursomc.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {

    @Autowired
    private EnderecoService service;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Endereco> find(@PathVariable Integer id){
        Endereco obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
