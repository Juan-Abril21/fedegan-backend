package com.example.fedegan.service;

import com.example.fedegan.orm.FincaORM;
import com.example.fedegan.respository.FincaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FincaService {
    private final FincaRepository fincaRepository;

    public FincaService(FincaRepository fincaRepository) {
        this.fincaRepository = fincaRepository;
    }

    public List<FincaORM> obtenerFincas() {
        return fincaRepository.findAll();
    }

    public FincaORM agregarFinca(FincaORM finca) {
        return fincaRepository.save(finca);
    }

    public FincaORM obtenerFincaPorId(Long id) {
        return fincaRepository.findById(id).orElse(null);
    }

    public void eliminarFinca(Long id) {
        fincaRepository.deleteById(id);
    }
}
