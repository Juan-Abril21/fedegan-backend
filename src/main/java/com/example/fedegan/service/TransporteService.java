package com.example.fedegan.service;

import com.example.fedegan.orm.TransporteORM;
import com.example.fedegan.respository.TransporteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransporteService {

    private final TransporteRepository transporteRepository;

    public TransporteService(TransporteRepository transporteRepository) {
        this.transporteRepository = transporteRepository;
    }

    public List<TransporteORM> obtenerTransportes() {
        return transporteRepository.findAll();
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
