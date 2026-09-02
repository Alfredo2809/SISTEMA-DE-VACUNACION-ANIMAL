package org.esfe.vacunacion.controladores;

import org.esfe.vacunacion.modelos.CampanaVacunacion;
import org.esfe.vacunacion.servicios.interfaces.ICampanaVacunacionService; // o ICampanaVacunacionService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campanas")
public class CampanaVacunacionController {

    @Autowired
    private ICampanaVacunacionService campanaVacunacionService;

    @GetMapping
    public ResponseEntity<List<CampanaVacunacion>> listar() {
        return ResponseEntity.ok(campanaVacunacionService.listar()); // Revisa si el método se llama obtenerTodas() o listar()
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampanaVacunacion> buscarPorId(@PathVariable Long id) {
        return campanaVacunacionService.buscarPorId(id)
                .map(campana -> ResponseEntity.ok(campana))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CampanaVacunacion> guardar(@RequestBody CampanaVacunacion campana) {
        CampanaVacunacion nuevaCampana = campanaVacunacionService.guardar(campana);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCampana);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (campanaVacunacionService.buscarPorId(id).isPresent()) {
            campanaVacunacionService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}