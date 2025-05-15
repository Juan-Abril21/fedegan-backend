package com.example.fedegan.service;

import com.example.fedegan.dto.RegistroVacunacionDTO;
import com.example.fedegan.orm.RegistroVacunacionORM;
import com.example.fedegan.respository.AnimalRepository;
import com.example.fedegan.respository.RegistroVacunacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistroVacunacionService {
    private final RegistroVacunacionRepository registroVacunacionRepository;

    public RegistroVacunacionService(RegistroVacunacionRepository registroVacunacionRepository) {
        this.registroVacunacionRepository = registroVacunacionRepository;
    }

    public List<RegistroVacunacionDTO> obtenerRegistros() {
        return registroVacunacionRepository.findAll().stream()
                .map(registro -> new RegistroVacunacionDTO(
                        registro.getRegistro_id(),
                        registro.getVacunador().getVacunador_id(),
                        registro.getAnimal().getAnimal_id(),
                        registro.getCampania().getCampania_id(),
                        registro.getFecha_aplicacion(),
                        registro.getResultado(),
                        registro.getObservaciones(),
                        registro.isSincronizado()
                )).collect(Collectors.toList());
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
