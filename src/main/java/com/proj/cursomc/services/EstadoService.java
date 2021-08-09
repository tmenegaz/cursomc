package com.proj.cursomc.services;

import com.proj.cursomc.domain.Estado;
import com.proj.cursomc.repositories.EstadoRepository;
import com.proj.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repo;

    public Estado find(Integer id){
        Optional<Estado> obj = repo.findById(id);
       return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", tipo: " + Estado.class.getName()));
    }
}
