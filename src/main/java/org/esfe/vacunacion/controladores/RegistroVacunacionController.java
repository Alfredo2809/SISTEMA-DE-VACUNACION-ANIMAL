package org.esfe.vacunacion.controladores;

import org.esfe.vacunacion.modelos.RegistroVacunacion;
import org.esfe.vacunacion.servicios.interfaces.ICampanaVacunacionService;
import org.esfe.vacunacion.servicios.interfaces.IMascotaService;
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

    // Inyectamos los servicios de Mascota y Campaña para poblar los selectores
    @Autowired(required = false)
    private IMascotaService mascotaService;

    @Autowired(required = false)
    private ICampanaVacunacionService campanaVacunacionService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("registros", registroVacunacionService.listar());
        model.addAttribute("registro", new RegistroVacunacion());

        // Si existen los servicios, pasamos las listas a la vista
        if (mascotaService != null) {
            model.addAttribute("mascotas", mascotaService.obtenerTodas());
        }
        if (campanaVacunacionService != null) {
            model.addAttribute("campanas", campanaVacunacionService.listar());
        }

        return "registroVacunacion/index";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("registro") RegistroVacunacion registro) {
        registroVacunacionService.guardar(registro);
        return "redirect:/registro-vacunacion";
    }
}