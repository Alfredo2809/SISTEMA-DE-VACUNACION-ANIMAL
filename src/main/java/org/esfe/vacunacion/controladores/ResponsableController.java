package org.esfe.vacunacion.controladores;

import jakarta.validation.Valid;
import org.esfe.vacunacion.modelos.Responsable;
import org.esfe.vacunacion.servicios.interfaces.IResponsableService;
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
        model.addAttribute("titulo", "Nuevo Responsable");
        model.addAttribute("responsable", new Responsable());
        return "responsables/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("responsable") Responsable responsable,
                          BindingResult result,
                          Model model,
                          RedirectAttributes redirectAttributes) {

        // 1. Validar duplicados por Documento de Identidad (Solo al crear un registro nuevo)
        if (responsable.getIdResponsable() == null &&
                responsableService.existsByDocumentoIdentidad(responsable.getDocumentoIdentidad())) {
            result.rejectValue("documentoIdentidad", "error.responsable", "Ya existe un responsable con este documento de identidad.");
        }

        // 2. Si existen errores de validación de campos o duplicados, recargar el formulario
        if (result.hasErrors()) {
            model.addAttribute("titulo", responsable.getIdResponsable() == null ? "Nuevo Responsable" : "Editar Responsable");
            return "responsables/formulario";
        }

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
        model.addAttribute("titulo", "Editar Responsable");
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