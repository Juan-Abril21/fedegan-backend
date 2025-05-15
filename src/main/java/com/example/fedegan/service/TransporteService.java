package com.example.fedegan.service;

import com.example.fedegan.dto.TransporteDTO;
import com.example.fedegan.orm.TransporteORM;
import com.example.fedegan.respository.TransporteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransporteService {

    private final TransporteRepository transporteRepository;

    public TransporteService(TransporteRepository transporteRepository) {
        this.transporteRepository = transporteRepository;
    }

    public List<TransporteDTO> obtenerTransportes() {
        return transporteRepository.findAll().stream()
                .map(transporte -> new TransporteDTO(
                        transporte.getTransporte_id(),
                        transporte.getAnimal().getAnimal_id(),
                        transporte.getFincaOrigen().getFinca_id(),
                        transporte.getFincaDestino().getFinca_id(),
                        transporte.getFechaTransporte(),
                        transporte.getMotivo()
                )).collect(Collectors.toList());
    }

    public void agregarTransporte(TransporteORM transporte) {
        transporteRepository.save(transporte);
    }

    public TransporteORM obtenerTransportePorId(Long id) {
        return transporteRepository.findById(id).orElse(null);
    }

    public void eliminarTransporte(Long id) {
        transporteRepository.deleteById(id);
    }
}
