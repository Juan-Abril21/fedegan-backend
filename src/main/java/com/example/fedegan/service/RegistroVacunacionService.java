package com.example.fedegan.service;

import com.example.fedegan.orm.RegistroVacunacionORM;
import com.example.fedegan.respository.RegistroVacunacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroVacunacionService {
    private final RegistroVacunacionRepository registroVacunacionRepository;

    public RegistroVacunacionService(RegistroVacunacionRepository registroVacunacionRepository) {
        this.registroVacunacionRepository = registroVacunacionRepository;
    }

    public List<RegistroVacunacionORM> obtenerRegistros() {
        return registroVacunacionRepository.findAll();
    }

    public void agregarRegistro(RegistroVacunacionORM registro) {
        registroVacunacionRepository.save(registro);
    }

    public RegistroVacunacionORM obtenerRegistroPorId(Long id) {
        return registroVacunacionRepository.findById(id).orElse(null);
    }

    public void eliminarRegistro(Long id) {
        registroVacunacionRepository.deleteById(id);
    }
}
