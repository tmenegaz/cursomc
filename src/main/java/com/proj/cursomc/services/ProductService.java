package com.proj.cursomc.services;

import com.proj.cursomc.domain.Product;
import com.proj.cursomc.repositories.ProductRepository;
import com.proj.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public Product find(Integer id){
        Optional<Product> obj = repo.findById(id);
       return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", tipo: " + Product.class.getName()));
    }
}
