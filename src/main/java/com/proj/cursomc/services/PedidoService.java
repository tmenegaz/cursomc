package com.proj.cursomc.services;

import com.proj.cursomc.domain.Pedido;
import com.proj.cursomc.repositories.PedidoRepository;
import com.proj.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    public Pedido find(Integer id){
        Optional<Pedido> obj = repo.findById(id);
       return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", tipo: " + Pedido.class.getName()));
    }
}
