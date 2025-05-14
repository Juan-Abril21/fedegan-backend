package com.example.fedegan.service;

import com.example.fedegan.orm.AnimalORM;
import com.example.fedegan.respository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<AnimalORM> obtenerAnimales() {
        return animalRepository.findAll();
    }

    public void agregarAnimal(AnimalORM animal) {
        animalRepository.save(animal);
    }

    public AnimalORM obtenerAnimalPorId(Long id) {
        return animalRepository.findById(id).orElse(null);
    }

    public void eliminarAnimal(Long id) {
        animalRepository.deleteById(id);
    }
}
