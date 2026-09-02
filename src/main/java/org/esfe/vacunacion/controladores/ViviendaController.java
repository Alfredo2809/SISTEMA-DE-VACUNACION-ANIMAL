package org.esfe.vacunacion.controladores;

import org.esfe.vacunacion.modelos.Vivienda;
import org.esfe.vacunacion.servicios.interfaces.IColoniaService;
import org.esfe.vacunacion.servicios.interfaces.IViviendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        model.addAttribute("vivienda", new Vivienda());
        model.addAttribute("colonias", coloniaService.obtenerTodos());
        return "viviendas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("vivienda") Vivienda vivienda, RedirectAttributes redirectAttributes) {
        viviendaService.guardar(vivienda);
        redirectAttributes.addFlashAttribute("mensajeExito", "Vivienda guardada correctamente.");
        return "redirect:/viviendas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("vivienda", viviendaService.buscarPorId(id));
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