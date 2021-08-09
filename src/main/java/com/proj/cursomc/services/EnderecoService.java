package com.proj.cursomc.services;

import com.proj.cursomc.domain.Endereco;
import com.proj.cursomc.repositories.EnderecoRepository;
import com.proj.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repo;

    public Endereco find(Integer id){
        Optional<Endereco> obj = repo.findById(id);
       return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", tipo: " + Endereco.class.getName()));
    }
}
