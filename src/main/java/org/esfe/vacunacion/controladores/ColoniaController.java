package org.esfe.vacunacion.controladores;

import org.esfe.vacunacion.modelos.Colonia;
import org.esfe.vacunacion.servicios.interfaces.ICantonService;
import org.esfe.vacunacion.servicios.interfaces.IColoniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/geografico/colonias")
public class ColoniaController {

    @Autowired
    private IColoniaService coloniaService;

    @Autowired
    private ICantonService cantonService;

    @GetMapping
    public String listar(@RequestParam(defaultValue = "0") int page, Model model) {

        Page<Colonia> coloniaPage = coloniaService.obtenerPaginados(PageRequest.of(page, 4));

        model.addAttribute("colonias", coloniaPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", coloniaPage.getTotalPages());
        model.addAttribute("totalItems", coloniaPage.getTotalElements());

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
        return "redirect:/geografico/colonias";
    }

    @GetMapping("/detalles/{id}")
    public String detalles(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Colonia> colonia = coloniaService.obtenerPorId(id);
        if (colonia.isPresent()) {
            model.addAttribute("colonia", colonia.get());
            return "geografico/colonias/detalles";
        }
        redirectAttributes.addFlashAttribute("mensajeError", "La colonia especificada no existe.");
        return "redirect:/geografico/colonias";
    }

    @PostMapping
    public String guardar(@ModelAttribute Colonia colonia, Model model, RedirectAttributes redirectAttributes) {
        try {
            coloniaService.guardar(colonia);
            redirectAttributes.addFlashAttribute("mensajeExito", "Colonia guardada exitosamente");
            return "redirect:/geografico/colonias";
        } catch (IllegalArgumentException e) {
            model.addAttribute("mensajeError", e.getMessage());
            model.addAttribute("colonia", colonia);
            model.addAttribute("cantones", cantonService.obtenerTodos());
            model.addAttribute("titulo", colonia.getIdColonia() != null ? "Editar Colonia" : "Nueva Colonia");
            return "geografico/colonias/formulario";
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error interno al procesar la colonia.");
            model.addAttribute("colonia", colonia);
            model.addAttribute("cantones", cantonService.obtenerTodos());
            model.addAttribute("titulo", colonia.getIdColonia() != null ? "Editar Colonia" : "Nueva Colonia");
            return "geografico/colonias/formulario";
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
        return "redirect:/geografico/colonias";
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
    public ResponseEntity<?> crearAPI(@RequestBody Colonia colonia) {
        try {
            Colonia guardado = coloniaService.guardar(colonia);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
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