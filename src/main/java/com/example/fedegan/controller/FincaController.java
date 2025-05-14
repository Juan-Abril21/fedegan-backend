package com.example.fedegan.controller;

import com.example.fedegan.orm.FincaORM;
import com.example.fedegan.service.FincaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fincas")
public class FincaController {
    private final FincaService fincaService;

    public FincaController(FincaService fincaService) {
        this.fincaService = fincaService;
    }

    @GetMapping
    public ResponseEntity<?> obtenerFincas() {
        try {
            List<FincaORM> fincas = fincaService.obtenerFincas();
            if (fincas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontraron fincas registradas");
            } else {
                return ResponseEntity.ok(fincas);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las fincas: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerFincaPorId(@PathVariable Long id) {
        try {
            FincaORM finca = fincaService.obtenerFincaPorId(id);
            if (finca != null) {
                return ResponseEntity.ok(finca);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Finca con ID " + id + " no encontrada");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la finca: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> agregarFinca(@RequestBody FincaORM finca) {
        try {
            // Validación general de todos los campos requeridos
            if (finca.getNombre_finca() == null || finca.getNombre_finca().isEmpty() ||
                    finca.getPropietario() == null || finca.getPropietario().isEmpty() ||
                    finca.getMunicipio() == null || finca.getMunicipio().isEmpty() ||
                    finca.getDepartamento() == null || finca.getDepartamento().isEmpty() ||
                    finca.getEstado() == null || finca.getEstado().isEmpty()) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Todos los campos son obligatorios. Por favor complete toda la información de la finca");
            }

            fincaService.agregarFinca(finca);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Finca agregada con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al agregar la finca: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarFinca(@PathVariable Long id) {
        try {
            FincaORM finca = fincaService.obtenerFincaPorId(id);
            if (finca == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se puede eliminar. Finca con ID " + id + " no encontrada");
            }

            fincaService.eliminarFinca(id);
            return ResponseEntity.ok("Finca eliminada con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la finca: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarFinca(@PathVariable Long id, @RequestBody FincaORM finca) {
        try {
            FincaORM fincaExistente = fincaService.obtenerFincaPorId(id);
            if (fincaExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Finca con ID " + id + " no encontrada");
            }

            // Validación general de todos los campos requeridos
            if (finca.getNombre_finca() == null || finca.getNombre_finca().isEmpty() ||
                    finca.getPropietario() == null || finca.getPropietario().isEmpty() ||
                    finca.getMunicipio() == null || finca.getMunicipio().isEmpty() ||
                    finca.getDepartamento() == null || finca.getDepartamento().isEmpty() ||
                    finca.getEstado() == null || finca.getEstado().isEmpty()) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Todos los campos son obligatorios. Por favor complete toda la información de la finca");
            }

            finca.setFinca_id(id);
            fincaService.agregarFinca(finca);
            return ResponseEntity.ok("Finca actualizada con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la finca: " + e.getMessage());
        }
    }
}