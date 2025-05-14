package com.example.fedegan.service;

import com.example.fedegan.orm.VacunadorORM;
import com.example.fedegan.respository.VacunadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacunadorService {
    private final VacunadorRepository vacunadorRepository;

    public VacunadorService(VacunadorRepository vacunadorRepository) {
        this.vacunadorRepository = vacunadorRepository;
    }

    public List<VacunadorORM> obtenerVacunadores() {
        return vacunadorRepository.findAll();
    }

    public void agregarVacunador(VacunadorORM vacunador) {
        vacunadorRepository.save(vacunador);
    }

    public VacunadorORM obtenerVacunadorPorId(Long id) {
        return vacunadorRepository.findById(id).orElse(null);
    }

    public void eliminarVacunador(Long id) {
        vacunadorRepository.deleteById(id);
    }
}
