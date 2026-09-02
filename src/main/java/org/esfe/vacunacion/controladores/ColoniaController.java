package org.esfe.vacunacion.controladores;

import org.esfe.vacunacion.modelos.Colonia;
import org.esfe.vacunacion.servicios.interfaces.ICantonService;
import org.esfe.vacunacion.servicios.interfaces.IColoniaService;
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
@RequestMapping("/colonias")
public class ColoniaController {

    @Autowired
    private IColoniaService coloniaService;

    @Autowired
    private ICantonService cantonService;


    @GetMapping
    public String listar(Model model) {
        model.addAttribute("colonias", coloniaService.obtenerTodos());
        return "geografico/colonias/lista";
    }

    @GetMapping("/crear")
    public String formularioCrear(Model model) {
        model.addAttribute("colonia", new Colonia());
        model.addAttribute("cantones", cantonService.obtenerTodos());
        model.addAttribute("titulo", "Nueva Colonia");
        return "geografico/colonias/formulario";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Colonia> colonia = coloniaService.obtenerPorId(id);
        if (colonia.isPresent()) {
            model.addAttribute("colonia", colonia.get());
            model.addAttribute("cantones", cantonService.obtenerTodos());
            model.addAttribute("titulo", "Editar Colonia");
            return "geografico/colonias/formulario";
        }
        redirectAttributes.addFlashAttribute("mensajeError", "La colonia especificada no existe.");
        return "redirect:/colonias";
    }

    @PostMapping
    public String guardar(@ModelAttribute Colonia colonia, RedirectAttributes redirectAttributes) {
        try {
            coloniaService.guardar(colonia);
            redirectAttributes.addFlashAttribute("mensajeExito", "Colonia guardada exitosamente");
            return "redirect:/colonias";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
            if (colonia.getIdColonia() != null) {
                return "redirect:/colonias/editar/" + colonia.getIdColonia();
            }
            return "redirect:/colonias/crear";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error interno al procesar la colonia.");
            return "redirect:/colonias";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            coloniaService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensajeExito", "Colonia eliminada correctamente");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se puede eliminar la colonia debido a registros relacionados.");
        }
        return "redirect:/colonias";
    }


    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Colonia>> obtenerTodosAPI() {
        return ResponseEntity.ok(coloniaService.obtenerTodos());
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Colonia> obtenerPorIdAPI(@PathVariable Long id) {
        return coloniaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Colonia> crearAPI(@RequestBody Colonia colonia) {
        try {
            Colonia guardado = coloniaService.guardar(colonia);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> eliminarAPI(@PathVariable Long id) {
        try {
            coloniaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}