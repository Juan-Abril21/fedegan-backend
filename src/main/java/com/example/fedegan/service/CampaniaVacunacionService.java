package com.example.fedegan.service;

import com.example.fedegan.orm.CampaniaVacunacionORM;
import com.example.fedegan.respository.CampaniaVacunacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaniaVacunacionService {

    private final CampaniaVacunacionRepository campaniaVacunacionRepository;

    public CampaniaVacunacionService(CampaniaVacunacionRepository campaniaVacunacionRepository) {
        this.campaniaVacunacionRepository = campaniaVacunacionRepository;
    }

    public List<CampaniaVacunacionORM> obtenerCampaniasVacunaciones() {
        return campaniaVacunacionRepository.findAll();
    }

    public void agregarCampaniaVacunacion(CampaniaVacunacionORM campaniaVacunacion) {
        campaniaVacunacionRepository.save(campaniaVacunacion);
    }

    public CampaniaVacunacionORM obtenerCampaniaVacunacionPorId(Long id) {
        return campaniaVacunacionRepository.findById(id).orElse(null);
    }

    public void eliminarCampaniaVacunacion(Long id) {
        campaniaVacunacionRepository.deleteById(id);
    }
}
