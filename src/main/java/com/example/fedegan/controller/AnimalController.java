package com.example.fedegan.controller;

import com.example.fedegan.dto.AnimalDTO;
import com.example.fedegan.orm.AnimalORM;
import com.example.fedegan.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animales")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public ResponseEntity<?> obtenerAnimales() {
        List<AnimalDTO> animales = animalService.obtenerAnimales();
        if (animales.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron animales registrados");
        } else {
            return ResponseEntity.ok(animales);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerAnimalPorId(@PathVariable Long id) {
        try {
            AnimalORM animal = animalService.obtenerAnimalPorId(id);
            if (animal != null) {
                return ResponseEntity.ok(animal);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Animal con ID " + id + " no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener el animal: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> agregarAnimal(@RequestBody AnimalORM animal) {
        try {
            // Validación general de todos los campos requeridos
            if (animal.getTipo_animal() == null || animal.getTipo_animal().isEmpty() ||
                    animal.getRaza() == null || animal.getRaza().isEmpty() ||
                    animal.getSexo() == null || animal.getSexo().isEmpty() ||
                    animal.getEdad_meses() < 0 ||
                    animal.getFinca() == null) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Todos los campos son obligatorios. Por favor complete toda la información del animal");
            }

            animalService.agregarAnimal(animal);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Animal agregado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al agregar el animal: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarAnimal(@PathVariable Long id) {
        try {
            AnimalORM animal = animalService.obtenerAnimalPorId(id);
            if (animal == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se puede eliminar. Animal con ID " + id + " no encontrado");
            }

            animalService.eliminarAnimal(id);
            return ResponseEntity.ok("Registro eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el animal: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarAnimal(@PathVariable Long id, @RequestBody AnimalORM animal) {
        try {
            AnimalORM animalExistente = animalService.obtenerAnimalPorId(id);
            if (animalExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Animal con ID " + id + " no encontrado");
            }

            // Validación general de todos los campos requeridos
            if (animal.getTipo_animal() == null || animal.getTipo_animal().isEmpty() ||
                    animal.getRaza() == null || animal.getRaza().isEmpty() ||
                    animal.getSexo() == null || animal.getSexo().isEmpty() ||
                    animal.getEdad_meses() < 0 ||
                    animal.getFinca() == null) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Todos los campos son obligatorios. Por favor complete toda la información del animal");
            }

            animal.setAnimal_id(id);
            animalService.agregarAnimal(animal);
            return ResponseEntity.ok("Animal actualizado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el animal: " + e.getMessage());
        }
    }
}