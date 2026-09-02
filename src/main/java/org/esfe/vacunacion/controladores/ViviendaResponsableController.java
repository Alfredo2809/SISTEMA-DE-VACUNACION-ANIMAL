package org.esfe.vacunacion.controladores;

import org.esfe.vacunacion.modelos.Responsable;
import org.esfe.vacunacion.modelos.Vivienda;
import org.esfe.vacunacion.modelos.ViviendaResponsable;
import org.esfe.vacunacion.modelos.ViviendaResponsableId;
import org.esfe.vacunacion.servicios.interfaces.IResponsableService;
import org.esfe.vacunacion.servicios.interfaces.IViviendaResponsableService;
import org.esfe.vacunacion.servicios.interfaces.IViviendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vivienda-responsable")
public class ViviendaResponsableController {

    @Autowired
    private IViviendaResponsableService viviendaResponsableService;

    @Autowired
    private IViviendaService viviendaService;

    @Autowired
    private IResponsableService responsableService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("asociaciones", viviendaResponsableService.listarTodos());
        return "vivienda-responsable/lista";
    }

    @GetMapping("/asociar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("viviendas", viviendaService.listarTodas());
        model.addAttribute("responsables", responsableService.listarTodos());
        return "vivienda-responsable/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam Long idVivienda,
                          @RequestParam Long idResponsable,
                          RedirectAttributes redirectAttributes) {

        if (viviendaResponsableService.existeAsociacion(idVivienda, idResponsable)) {
            redirectAttributes.addFlashAttribute("mensajeError", "Esta asociacion ya existe.");
            return "redirect:/vivienda-responsable/asociar";
        }

        Vivienda vivienda = viviendaService.buscarPorId(idVivienda);
        Responsable responsable = responsableService.buscarPorId(idResponsable);

        if (vivienda == null || responsable == null) {
            redirectAttributes.addFlashAttribute("mensajeError", "Vivienda o Responsable no encontrado.");
            return "redirect:/vivienda-responsable/asociar";
        }

        ViviendaResponsable nuevaAsociacion = new ViviendaResponsable();
        nuevaAsociacion.setId(new ViviendaResponsableId(idVivienda, idResponsable));
        nuevaAsociacion.setVivienda(vivienda);
        nuevaAsociacion.setResponsable(responsable);

        viviendaResponsableService.guardar(nuevaAsociacion);
        redirectAttributes.addFlashAttribute("mensajeExito", "Responsable asociado a la vivienda correctamente.");
        return "redirect:/vivienda-responsable/asociar";
    }
}