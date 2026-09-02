package org.esfe.vacunacion.controladores;

import org.esfe.vacunacion.modelos.Departamento;
import org.esfe.vacunacion.servicios.interfaces.IDepartamentoService;
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
@RequestMapping("/geografico/departamentos")
public class DepartamentoController {

    @Autowired
    private IDepartamentoService departamentoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("departamentos", departamentoService.obtenerTodos());
        return "geografico/departamentos/lista";
    }

    @GetMapping("/crear")
    public String formularioCrear(Model model) {
        model.addAttribute("departamento", new Departamento());
        model.addAttribute("titulo", "Nuevo Departamento");
        return "geografico/departamentos/formulario";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Departamento> departamento = departamentoService.obtenerPorId(id);
        if (departamento.isPresent()) {
            model.addAttribute("departamento", departamento.get());
            model.addAttribute("titulo", "Editar Departamento");
            return "geografico/departamentos/formulario";
        }
        redirectAttributes.addFlashAttribute("mensajeError", "El departamento especificado no existe.");
        return "redirect:/geografico/departamentos";
    }

    @GetMapping("/detalles/{id}")
    public String detalles(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Departamento> departamento = departamentoService.obtenerPorId(id);
        if (departamento.isPresent()) {
            model.addAttribute("departamento", departamento.get());
            return "geografico/departamentos/detalles";
        }
        redirectAttributes.addFlashAttribute("mensajeError", "El departamento especificado no existe.");
        return "redirect:/geografico/departamentos";
    }

    @PostMapping
    public String guardar(@ModelAttribute Departamento departamento, RedirectAttributes redirectAttributes) {
        try {
            departamentoService.guardar(departamento);
            redirectAttributes.addFlashAttribute("mensajeExito", "Departamento guardado exitosamente");
            return "redirect:/geografico/departamentos";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
            if (departamento.getIdDepartamento() != null) {
                return "redirect:/geografico/departamentos/editar/" + departamento.getIdDepartamento();
            }
            return "redirect:/geografico/departamentos/crear";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error interno al procesar el departamento.");
            return "redirect:/geografico/departamentos";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            departamentoService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensajeExito", "Departamento eliminado correctamente");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se puede eliminar el departamento porque posee municipios vinculados.");
        }
        return "redirect:/geografico/departamentos";
    }

    // --- MÉTODOS API REST ---
    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Departamento>> obtenerTodosAPI() {
        return ResponseEntity.ok(departamentoService.obtenerTodos());
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Departamento> obtenerPorIdAPI(@PathVariable Long id) {
        return departamentoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Departamento> crearAPI(@RequestBody Departamento departamento) {
        try {
            Departamento guardado = departamentoService.guardar(departamento);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> eliminarAPI(@PathVariable Long id) {
        try {
            departamentoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
