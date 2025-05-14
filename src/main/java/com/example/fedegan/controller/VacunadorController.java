package com.example.fedegan.controller;

import com.example.fedegan.orm.AnimalORM;
import com.example.fedegan.orm.VacunadorORM;
import com.example.fedegan.service.VacunadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/vacunadores")
public class VacunadorController {
    private final VacunadorService vacunadorService;

    public VacunadorController(VacunadorService vacunadorService) {
        this.vacunadorService = vacunadorService;
    }

    @GetMapping
    public ResponseEntity<?> obtenerVacunadores() {
        List<VacunadorORM> vacunadores = vacunadorService.obtenerVacunadores();
        if (vacunadores.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron vacunadores registrados");
        } else {
            return ResponseEntity.ok(vacunadores);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerVacunadorPorId(@PathVariable Long id) {
        try {
            VacunadorORM vacunador = vacunadorService.obtenerVacunadorPorId(id);
            if (vacunador != null) {
                return ResponseEntity.ok(vacunador);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Vacunador con ID " + id + " no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener el vacunador: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> agregarVacunador(@RequestBody VacunadorORM vacunador) {
        try {
            // Validación general de todos los campos requeridos
            if (vacunador.getNombre_completo() == null || vacunador.getNombre_completo().isEmpty() ||
                    vacunador.getDocumento() <= 0 ||
                    vacunador.getTelefono() <= 0 ||
                    vacunador.getEmail() == null || vacunador.getEmail().isEmpty() ||
                    vacunador.getZona_asignada() == null || vacunador.getZona_asignada().isEmpty()) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Todos los campos son obligatorios. Por favor complete la información del vacunador");
            }

            // Establecer la fecha de registro como la fecha y hora actual
            vacunador.setFecha_registro(LocalDateTime.now());

            vacunadorService.agregarVacunador(vacunador);
            return ResponseEntity.status(HttpStatus.CREATED).body("Vacunador agregado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al agregar el vacunador: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarVacunador(@PathVariable Long id) {
        try {
            VacunadorORM vacunador = vacunadorService.obtenerVacunadorPorId(id);
            if (vacunador != null) {
                vacunadorService.eliminarVacunador(id);
                return ResponseEntity.ok("Vacunador eliminado exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Vacunador con ID " + id + " no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el vacunador: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarVacunador(@PathVariable Long id, @RequestBody VacunadorORM vacunador) {
        try {
            VacunadorORM vacunadorExistente = vacunadorService.obtenerVacunadorPorId(id);
            if (vacunadorExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Vacunador con ID " + id + " no encontrado");
            }

            // Validación general de todos los campos requeridos
            if (vacunador.getNombre_completo() == null || vacunador.getNombre_completo().isEmpty() ||
                    vacunador.getDocumento() <= 0 ||
                    vacunador.getTelefono() <= 0 ||
                    vacunador.getEmail() == null || vacunador.getEmail().isEmpty() ||
                    vacunador.getZona_asignada() == null || vacunador.getZona_asignada().isEmpty()) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Todos los campos son obligatorios. Por favor complete la información del vacunador");
            }

            vacunador.setFecha_registro(vacunadorExistente.getFecha_registro());
            vacunador.setVacunador_id(id);
            vacunadorService.agregarVacunador(vacunador);
            return ResponseEntity.ok("Vacunador actualizado con exito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el vacunador: " + e.getMessage());
        }
    }
}
