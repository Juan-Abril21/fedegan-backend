package com.example.fedegan.service;

import com.example.fedegan.dto.AnimalDTO;
import com.example.fedegan.orm.AnimalORM;
import com.example.fedegan.respository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<AnimalDTO> obtenerAnimales() {
        return animalRepository.findAll().stream()
                .map(animal -> new AnimalDTO(
                        animal.getAnimal_id(),
                        animal.getFinca().getFinca_id(),
                        animal.getTipo_animal(),
                        animal.getRaza(),
                        animal.getSexo(),
                        animal.getEdad_meses()
                ))
                .collect(Collectors.toList());
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
