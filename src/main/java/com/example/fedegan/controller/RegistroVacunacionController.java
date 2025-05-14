package com.example.fedegan.controller;

import com.example.fedegan.orm.RegistroVacunacionORM;
import com.example.fedegan.service.RegistroVacunacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/registrovacunacion")
public class RegistroVacunacionController {
    private final RegistroVacunacionService registroVacunacionService;

    public RegistroVacunacionController(RegistroVacunacionService registroVacunacionService) {
        this.registroVacunacionService = registroVacunacionService;
    }

    @GetMapping
    public ResponseEntity<?> obtenerRegistroVacunacion() {
        List<RegistroVacunacionORM> registros = registroVacunacionService.obtenerRegistros();
        if (registros.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron registros de vacunacion");
        } else {
            return ResponseEntity.ok(registros);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerRegistroVacunacionPorId(@PathVariable Long id) {
        try {
            RegistroVacunacionORM registro = registroVacunacionService.obtenerRegistroPorId(id);
            if (registro != null) {
                return ResponseEntity.ok(registro);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Registro de vacunacion con ID " + id + " no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener el registro de vacunacion: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> agregarRegistroVacunacion(@RequestBody RegistroVacunacionORM registro) {
        try {
            // Validación general de todos los campos requeridos
            if (registro.getVacunador() == null ||
                    registro.getAnimal() == null ||
                    registro.getCampania() == null ||
                    registro.getResultado() == null || registro.getResultado().isEmpty()) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Todos los campos son obligatorios. Por favor complete toda la información del registro de vacunación");
            }

            registroVacunacionService.agregarRegistro(registro);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Registro de vacunación creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el registro de vacunación: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRegistroVacunacion(@PathVariable Long id) {
        try {
            RegistroVacunacionORM registro = registroVacunacionService.obtenerRegistroPorId(id);
            if (registro != null) {
                registroVacunacionService.eliminarRegistro(id);
                return ResponseEntity.status(HttpStatus.OK).body("Registro de vacunacion eliminado exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Registro de vacunacion con ID " + id + " no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el registro de vacunacion: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarRegistroVacunacion(@PathVariable Long id, @RequestBody RegistroVacunacionORM registro) {
        try {
            RegistroVacunacionORM registroExistente = registroVacunacionService.obtenerRegistroPorId(id);
            if (registroExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Registro de vacunación con ID " + id + " no encontrado");
            }

            // Validación general de todos los campos requeridos
            if (registro.getVacunador() == null ||
                    registro.getAnimal() == null ||
                    registro.getCampania() == null ||
                    registro.getResultado() == null || registro.getResultado().isEmpty()) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Todos los campos son obligatorios. Por favor complete toda la información del registro de vacunación");
            }

            // Mantener la fecha de aplicación original si no se especifica una nueva
            if (registro.getFecha_aplicacion() == null) {
                registro.setFecha_aplicacion(registroExistente.getFecha_aplicacion());
            }

            registro.setRegistro_id(id);
            registroVacunacionService.agregarRegistro(registro);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Registro de vacunación actualizado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el registro de vacunación: " + e.getMessage());
        }
    }
}
