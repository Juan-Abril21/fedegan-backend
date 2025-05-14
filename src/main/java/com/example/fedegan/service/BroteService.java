package com.example.fedegan.service;

import com.example.fedegan.orm.BroteORM;
import com.example.fedegan.respository.BroteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BroteService {
    private final BroteRepository broteRepository;

    public BroteService(BroteRepository broteRepository) {
        this.broteRepository = broteRepository;
    }

    public List<BroteORM> obtenerBrotes() {
        return broteRepository.findAll();
    }

    public void agregarBrote(BroteORM brote) {
        broteRepository.save(brote);
    }

    public BroteORM obtenerBrotePorId(Long id) {
        return broteRepository.findById(id).orElse(null);
    }

    public void eliminarBrote(Long id) {
        broteRepository.deleteById(id);
    }
}
