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
    private ICampanaVacunacionService campanaService;

    // Listar todas las campañas
    @GetMapping
    public String index(Model model) {
        model.addAttribute("campanas", campanaService.listar());
        return "campana/lista";
    }

    // Mostrar formulario para nueva campaña
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("campana", new CampanaVacunacion());
        return "campana/formulario";
    }

    // Guardar o actualizar campaña
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("campana") CampanaVacunacion campana) {
        campanaService.guardar(campana);
        return "redirect:/campanas";
    }

    // Mostrar formulario para editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        CampanaVacunacion campana = campanaService.buscarPorId(id).orElse(new CampanaVacunacion());
        model.addAttribute("campana", campana);
        return "campana/formulario";
    }

    // Eliminar campaña
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        campanaService.eliminar(id);
        return "redirect:/campanas";
    }
}