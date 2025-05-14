package com.example.fedegan.controller;

import com.example.fedegan.orm.BroteORM;
import com.example.fedegan.service.BroteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brote")
public class BroteController {

    private final BroteService broteService;

    public BroteController(BroteService broteService) {
        this.broteService = broteService;
    }

    @GetMapping
    public ResponseEntity<?> obtenerBrotes() {
        List<BroteORM> brote = broteService.obtenerBrotes();
        if (brote.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron brotes registrados");
        } else {
            return ResponseEntity.ok(brote);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerBrotePorId(@PathVariable Long id) {
        try {
            BroteORM brote = broteService.obtenerBrotePorId(id);
            if (brote != null) {
                return ResponseEntity.ok(brote);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Brote con ID " + id + " no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener el brote: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> agregarBrote(@RequestBody BroteORM brote) {
        try {
            // Validación general de todos los campos requeridos
            if (brote.getMunicipio() == null || brote.getMunicipio().isEmpty() ||
                    brote.getFecha_reporte() == null ||
                    brote.getTipo_enfermedad() == null || brote.getTipo_enfermedad().isEmpty() ||
                    brote.getNumero_casos() <= 0 ||
                    brote.getEstado_brote() == null || brote.getEstado_brote().isEmpty()) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Todos los campos son obligatorios. Por favor complete toda la información del brote");
            }

            broteService.agregarBrote(brote);
            return ResponseEntity.status(HttpStatus.CREATED).body("Brote agregado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al agregar el brote: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarBrote(@PathVariable Long id) {
        try {
            BroteORM brote = broteService.obtenerBrotePorId(id);
            if (brote != null) {
                broteService.eliminarBrote(id);
                return ResponseEntity.status(HttpStatus.OK).body("Brote eliminado exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Brote con ID " + id + " no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el brote: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarBrote(@PathVariable Long id, @RequestBody BroteORM brote) {
        try {
            BroteORM broteExistente = broteService.obtenerBrotePorId(id);
            if (broteExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Brote con ID " + id + " no encontrado");
            }

            // Validación general de todos los campos requeridos
            if (brote.getMunicipio() == null || brote.getMunicipio().isEmpty() ||
                    brote.getFecha_reporte() == null ||
                    brote.getTipo_enfermedad() == null || brote.getTipo_enfermedad().isEmpty() ||
                    brote.getNumero_casos() <= 0 ||
                    brote.getEstado_brote() == null || brote.getEstado_brote().isEmpty()) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Todos los campos son obligatorios. Por favor complete toda la información del brote");
            }

            brote.setBrote_id(broteExistente.getBrote_id());
            broteService.agregarBrote(brote);
            return ResponseEntity.status(HttpStatus.OK).body("Brote actualizado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el brote: " + e.getMessage());
        }
    }
}
