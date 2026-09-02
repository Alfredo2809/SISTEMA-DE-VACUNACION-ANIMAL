package org.esfe.vacunacion.controladores;

import org.esfe.vacunacion.modelos.RegistroVacunacion;
import org.esfe.vacunacion.servicios.interfaces.IRegistroVacunacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registro-vacunacion")
public class RegistroVacunacionController {

    @Autowired
    private IRegistroVacunacionService registroVacunacionService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("registros", registroVacunacionService.listar());
        model.addAttribute("registro", new RegistroVacunacion());
        return "registroVacunacion/index";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("registro") RegistroVacunacion registro) {
        registroVacunacionService.guardar(registro);
        return "redirect:/registro-vacunacion";
    }
}
