package org.esfe.vacunacion.controladores;

import org.esfe.vacunacion.modelos.EstadoVacunacion;
import org.esfe.vacunacion.modelos.RegistroVacunacion;
import org.esfe.vacunacion.servicios.interfaces.ICampanaVacunacionService;
import org.esfe.vacunacion.servicios.interfaces.IMascotaService;
import org.esfe.vacunacion.servicios.interfaces.IRegistroVacunacionService;
import org.esfe.vacunacion.servicios.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/registros-vacunacion")
public class RegistroVacunacionController {

    @Autowired
    private IRegistroVacunacionService registroService;

    @Autowired(required = false)
    private IMascotaService mascotaService;

    @Autowired(required = false)
    private ICampanaVacunacionService campanaService;

    @Autowired(required = false)
    private IUsuarioService usuarioService;

    // Listar todos los registros
    @GetMapping
    public String index(Model model) {
        model.addAttribute("registros", registroService.listar());
        return "registro-vacunacion/lista";
    }

    // Mostrar formulario para nuevo registro
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        RegistroVacunacion registro = new RegistroVacunacion();
        registro.setFechaRegistro(LocalDateTime.now());

        model.addAttribute("registro", registro);
        model.addAttribute("estados", EstadoVacunacion.values());

        if (mascotaService != null) model.addAttribute("mascotas", mascotaService.obtenerTodas());
        if (campanaService != null) model.addAttribute("campanas", campanaService.listar());
        if (usuarioService != null) model.addAttribute("usuarios", usuarioService.obtenerTodos());

        return "registro-vacunacion/formulario";
    }

    // Guardar o actualizar registro
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("registro") RegistroVacunacion registro) {
        if (registro.getFechaRegistro() == null) {
            registro.setFechaRegistro(LocalDateTime.now());
        }
        registroService.guardar(registro);
        return "redirect:/registros-vacunacion";
    }

    // Editar registro
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        RegistroVacunacion registro = registroService.buscarPorId(id).orElse(new RegistroVacunacion());

        model.addAttribute("registro", registro);
        model.addAttribute("estados", EstadoVacunacion.values());

        if (mascotaService != null) model.addAttribute("mascotas", mascotaService.obtenerTodas());
        if (campanaService != null) model.addAttribute("campanas", campanaService.listar());
        if (usuarioService != null) model.addAttribute("usuarios", usuarioService.obtenerTodos());

        return "registro-vacunacion/formulario";
    }

    // Eliminar registro
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        registroService.eliminar(id);
        return "redirect:/registros-vacunacion";
    }
}