package org.esfe.vacunacion.controladores;

import org.esfe.vacunacion.modelos.Responsable;
import org.esfe.vacunacion.servicios.interfaces.IResponsableService;
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
@RequestMapping("/responsables")
public class ResponsableController {

    @Autowired
    private IResponsableService responsableService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("responsables", responsableService.listarTodos());
        return "responsables/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("responsable", new Responsable());
        return "responsables/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("responsable") Responsable responsable, RedirectAttributes redirectAttributes) {
        responsableService.guardar(responsable);
        redirectAttributes.addFlashAttribute("mensajeExito", "Responsable guardado correctamente.");
        return "redirect:/responsables";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Responsable responsable = responsableService.buscarPorId(id);
        if (responsable == null) {
            redirectAttributes.addFlashAttribute("mensajeError", "Responsable no encontrado.");
            return "redirect:/responsables";
        }
        model.addAttribute("responsable", responsable);
        return "responsables/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        responsableService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensajeExito", "Responsable eliminado correctamente.");
        return "redirect:/responsables";
    }
}