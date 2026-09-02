package org.esfe.vacunacion.controladores;

import org.esfe.vacunacion.modelos.CampanaVacunacion;
import org.esfe.vacunacion.servicios.interfaces.ICampanaVacunacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/campanas")
public class CampanaVacunacionController {

    @Autowired
    private ICampanaVacunacionService campanaVacunacionService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("campanas", campanaVacunacionService.listar());
        model.addAttribute("campana", new CampanaVacunacion());
        return "campanas/index"; // Ruta a la plantilla HTML
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("campana") CampanaVacunacion campana) {
        campanaVacunacionService.guardar(campana);
        return "redirect:/campanas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        campanaVacunacionService.buscarPorId(id).ifPresent(c -> model.addAttribute("campana", c));
        model.addAttribute("campanas", campanaVacunacionService.listar());
        return "campanas/index";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        campanaVacunacionService.eliminar(id);
        return "redirect:/campanas";
    }
}