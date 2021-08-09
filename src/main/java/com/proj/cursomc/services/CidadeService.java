package com.proj.cursomc.services;

import com.proj.cursomc.domain.Cidade;
import com.proj.cursomc.repositories.CidadeRepository;
import com.proj.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repo;

    public Cidade find(Integer id){
        Optional<Cidade> obj = repo.findById(id);
       return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", tipo: " + Cidade.class.getName()));
    }
}
