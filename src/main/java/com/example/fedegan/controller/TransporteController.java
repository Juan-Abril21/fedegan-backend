package com.example.fedegan.controller;

import com.example.fedegan.orm.TransporteORM;
import com.example.fedegan.service.TransporteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transportes")
public class TransporteController {

    private final TransporteService transporteService;

    public TransporteController(TransporteService transporteService) {
        this.transporteService = transporteService;
    }

    @GetMapping
    public ResponseEntity<?> obtenerTransportes() {
        List<TransporteORM> transportes = transporteService.obtenerTransportes();
        if (transportes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron transportes registrados");
        } else {
            return ResponseEntity.ok(transportes);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerTransportePorId(@PathVariable Long id) {
        try {
            TransporteORM transporte = transporteService.obtenerTransportePorId(id);
            if (transporte != null) {
                return ResponseEntity.ok(transporte);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transporte con ID " + id + " no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener el transporte: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> agregarTransporte(@RequestBody TransporteORM transporte) {
        try {
            // Validación general de todos los campos requeridos
            if (transporte.getAnimal() == null ||
                    transporte.getFincaOrigen() == null ||
                    transporte.getFincaDestino() == null ||
                    transporte.getFecha_transporte() == null ||
                    transporte.getMotivo() == null || transporte.getMotivo().isEmpty()) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Todos los campos son obligatorios. Por favor complete toda la información del transporte");
            }

            if (transporte.getFincaOrigen().getFinca_id() == null ||
                    transporte.getFincaDestino().getFinca_id() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Los IDs de las fincas de origen y destino no pueden ser nulos");
            }

            // Validar que origen y destino sean diferentes
            if (transporte.getFincaOrigen().getFinca_id().equals(transporte.getFincaDestino().getFinca_id())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El origen y destino no pueden ser la misma finca");
            }

            transporteService.agregarTransporte(transporte);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Transporte agregado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al agregar el transporte: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTransporte(@PathVariable Long id) {
        try {
            TransporteORM transporte = transporteService.obtenerTransportePorId(id);
            if (transporte != null) {
                transporteService.eliminarTransporte(id);
                return ResponseEntity.ok("Transporte eliminado con éxito");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transporte con ID " + id + " no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el transporte: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarTransporte(@PathVariable Long id, @RequestBody TransporteORM transporte) {
        try {
            TransporteORM transporteExistente = transporteService.obtenerTransportePorId(id);
            if (transporteExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Transporte con ID " + id + " no encontrado");
            }

            // Validación general de todos los campos requeridos
            if (transporte.getAnimal() == null ||
                    transporte.getFincaOrigen() == null ||
                    transporte.getFincaDestino() == null ||
                    transporte.getFecha_transporte() == null ||
                    transporte.getMotivo() == null || transporte.getMotivo().isEmpty()) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Todos los campos son obligatorios. Por favor complete toda la información del transporte");
            }

            if (transporte.getFincaOrigen().getFinca_id() == null ||
                    transporte.getFincaDestino().getFinca_id() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Los IDs de las fincas de origen y destino no pueden ser nulos");
            }

            // Validar que origen y destino sean diferentes
            if (transporte.getFincaOrigen().getFinca_id().equals(transporte.getFincaDestino().getFinca_id())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El origen y destino no pueden ser la misma finca");
            }

            transporte.setTransporte_id(id);
            transporteService.agregarTransporte(transporte);
            return ResponseEntity.ok("Transporte actualizado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el transporte: " + e.getMessage());
        }
    }
}
