package org.esfe.vacunacion.controladores;

import jakarta.validation.Valid;
import org.esfe.vacunacion.modelos.Vivienda;
import org.esfe.vacunacion.servicios.interfaces.IColoniaService;
import org.esfe.vacunacion.servicios.interfaces.IViviendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/viviendas")
public class ViviendaController {

    @Autowired
    private IViviendaService viviendaService;

    @Autowired
    private IColoniaService coloniaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("viviendas", viviendaService.listarTodas());
        return "viviendas/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("titulo", "Nueva Vivienda");
        model.addAttribute("vivienda", new Vivienda());
        model.addAttribute("colonias", coloniaService.obtenerTodos());
        return "viviendas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("vivienda") Vivienda vivienda,
                          BindingResult result,
                          Model model,
                          RedirectAttributes redirectAttributes) {

        // 1. Detectar dirección duplicada (solo cuando es una vivienda nueva)
        if (vivienda.getIdVivienda() == null &&
                vivienda.getDireccion() != null &&
                viviendaService.existsByDireccion(vivienda.getDireccion().trim())) {
            result.rejectValue("direccion", "error.vivienda", "Esta dirección ya se encuentra registrada en el sistema.");
        }

        // 2. Si existen errores de validación, recargar el formulario con el listado de colonias
        if (result.hasErrors()) {
            model.addAttribute("titulo", vivienda.getIdVivienda() == null ? "Nueva Vivienda" : "Editar Vivienda");
            model.addAttribute("colonias", coloniaService.obtenerTodos());
            return "viviendas/formulario";
        }

        viviendaService.guardar(vivienda);
        redirectAttributes.addFlashAttribute("mensajeExito", "Vivienda guardada correctamente.");
        return "redirect:/viviendas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Vivienda vivienda = viviendaService.buscarPorId(id);
        if (vivienda == null) {
            redirectAttributes.addFlashAttribute("mensajeError", "Vivienda no encontrada.");
            return "redirect:/viviendas";
        }
        model.addAttribute("titulo", "Editar Vivienda");
        model.addAttribute("vivienda", vivienda);
        model.addAttribute("colonias", coloniaService.obtenerTodos());
        return "viviendas/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        viviendaService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensajeExito", "Vivienda eliminada correctamente.");
        return "redirect:/viviendas";
    }
}