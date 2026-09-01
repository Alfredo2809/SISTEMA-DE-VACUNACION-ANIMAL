package org.esfe.vacunacion.controladores;

import org.esfe.vacunacion.modelos.Canton;
import org.esfe.vacunacion.servicios.interfaces.ICantonService;
import org.esfe.vacunacion.servicios.interfaces.IMunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cantones")
public class CantonController {

    @Autowired
    private ICantonService cantonService;

    @Autowired
    private IMunicipioService municipioService;


    @GetMapping
    public String listar(Model model) {
        model.addAttribute("cantones", cantonService.obtenerTodos());
        return "geografico/cantones/lista";
    }

    @GetMapping("/crear")
    public String formularioCrear(Model model) {
        model.addAttribute("canton", new Canton());
        model.addAttribute("municipios", municipioService.obtenerTodos());
        model.addAttribute("titulo", "Nuevo Cantón");
        return "geografico/cantones/formulario";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Canton> canton = cantonService.obtenerPorId(id);
        if (canton.isPresent()) {
            model.addAttribute("canton", canton.get());
            model.addAttribute("municipios", municipioService.obtenerTodos());
            model.addAttribute("titulo", "Editar Cantón");
            return "geografico/cantones/formulario";
        }
        redirectAttributes.addFlashAttribute("mensajeError", "El cantón especificado no existe.");
        return "redirect:/cantones";
    }

    @PostMapping
    public String guardar(@ModelAttribute Canton canton, RedirectAttributes redirectAttributes) {
        try {
            cantonService.guardar(canton);
            redirectAttributes.addFlashAttribute("mensajeExito", "Cantón guardado exitosamente");
            return "redirect:/cantones";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
            if (canton.getIdCanton() != null) {
                return "redirect:/cantones/editar/" + canton.getIdCanton();
            }
            return "redirect:/cantones/crear";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error interno al procesar el cantón.");
            return "redirect:/cantones";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            cantonService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensajeExito", "Cantón eliminado correctamente");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se puede eliminar el cantón debido a registros relacionados.");
        }
        return "redirect:/cantones";
    }


    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Canton>> obtenerTodosAPI() {
        return ResponseEntity.ok(cantonService.obtenerTodos());
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Canton> obtenerPorIdAPI(@PathVariable Long id) {
        return cantonService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Canton> crearAPI(@RequestBody Canton canton) {
        try {
            Canton guardado = cantonService.guardar(canton);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> eliminarAPI(@PathVariable Long id) {
        try {
            cantonService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}