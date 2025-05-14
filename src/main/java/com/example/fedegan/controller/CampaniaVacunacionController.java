package com.example.fedegan.controller;

import com.example.fedegan.orm.CampaniaVacunacionORM;
import com.example.fedegan.service.CampaniaVacunacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaniavacunacion")
public class CampaniaVacunacionController {
    private final CampaniaVacunacionService campaniaVacunacionService;

    public CampaniaVacunacionController(CampaniaVacunacionService campaniaVacunacionService) {
        this.campaniaVacunacionService = campaniaVacunacionService;
    }

    @GetMapping
    public ResponseEntity<?> obtenerCampanias() {
        List<CampaniaVacunacionORM> campanias = campaniaVacunacionService.obtenerCampaniasVacunaciones();
        if (campanias.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron campañas de vacunacion registradas");
        } else {
            return ResponseEntity.ok(campanias);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCampaniaPorId(@PathVariable Long id) {
        try {
            CampaniaVacunacionORM campania = campaniaVacunacionService.obtenerCampaniaVacunacionPorId(id);
            if (campania != null) {
                return ResponseEntity.ok(campania);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campaña de vacunación con ID " + id + " no encontrada");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener la campaña de vacunación: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> agregarCampania(@RequestBody CampaniaVacunacionORM campania) {
        try {
            // Validación general de todos los campos requeridos
            if (campania.getNombre() == null || campania.getNombre().isEmpty() ||
                    campania.getFecha_inicio() == null ||
                    campania.getFecha_fin() == null ||
                    campania.getEstado() == null || campania.getEstado().isEmpty()) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Todos los campos son obligatorios. Por favor complete toda la información de la campaña");
            }

            // Validar que la fecha de fin sea posterior a la fecha de inicio
            if (campania.getFecha_fin().before(campania.getFecha_inicio())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("La fecha de fin debe ser posterior a la fecha de inicio");
            }

            campaniaVacunacionService.agregarCampaniaVacunacion(campania);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Campaña de vacunación creada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la campaña de vacunación: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCampania(@PathVariable Long id) {
        try {
            CampaniaVacunacionORM campania = campaniaVacunacionService.obtenerCampaniaVacunacionPorId(id);
            if (campania != null) {
                campaniaVacunacionService.eliminarCampaniaVacunacion(id);
                return ResponseEntity.ok("Campaña de vacunación eliminada exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campaña de vacunación con ID " + id + " no encontrada");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la campaña de vacunación: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarCampania(@PathVariable Long id, @RequestBody CampaniaVacunacionORM campania) {
        try {
            CampaniaVacunacionORM campaniaExistente = campaniaVacunacionService.obtenerCampaniaVacunacionPorId(id);
            if (campaniaExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Campaña de vacunación con ID " + id + " no encontrada");
            }

            // Validación general de todos los campos requeridos
            if (campania.getNombre() == null || campania.getNombre().isEmpty() ||
                    campania.getFecha_inicio() == null ||
                    campania.getFecha_fin() == null ||
                    campania.getEstado() == null || campania.getEstado().isEmpty()) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Todos los campos son obligatorios. Por favor complete toda la información de la campaña");
            }

            // Validar que la fecha de fin sea posterior a la fecha de inicio
            if (campania.getFecha_fin().before(campania.getFecha_inicio())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("La fecha de fin debe ser posterior a la fecha de inicio");
            }

            campania.setCampania_id(id.intValue());
            campaniaVacunacionService.agregarCampaniaVacunacion(campania);
            return ResponseEntity.ok("Campaña de vacunación actualizada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la campaña de vacunación: " + e.getMessage());
        }
    }
}
