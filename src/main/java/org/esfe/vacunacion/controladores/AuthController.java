package org.esfe.vacunacion.controladores;

import org.esfe.vacunacion.modelos.EstadoUsuario;
import org.esfe.vacunacion.modelos.RolUsuario;
import org.esfe.vacunacion.modelos.Usuario;
import org.esfe.vacunacion.servicios.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/")
    public String inicio() {
        if (usuarioService.contarUsuarios() == 0) {
            return "redirect:/registro";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        if (usuarioService.contarUsuarios() == 0) {
            return "redirect:/registro";
        }
        return "sesion/login"; // Retorna la vista src/main/resources/templates/sesion/login.html
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model, RedirectAttributes redirectAttributes) {
        if (usuarioService.contarUsuarios() > 0) {
            redirectAttributes.addFlashAttribute("error", "Ya existe un usuario registrado en el sistema.");
            return "redirect:/login";
        }
        model.addAttribute("usuario", new Usuario());
        return "sesion/registro"; // Retorna la vista src/main/resources/templates/sesion/registro.html
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {
        if (usuarioService.contarUsuarios() > 0) {
            redirectAttributes.addFlashAttribute("error", "Solo se permite registrar un usuario administrador.");
            return "redirect:/login";
        }
        try { // <-- FALTABA ABRIR EL BLOQUE TRY AQUÍ
            usuario.setRol(RolUsuario.ADMIN);
            usuario.setEstado(EstadoUsuario.ACTIVO);
            usuario.setFechaCreacion(java.time.LocalDateTime.now());
            usuario.setFechaActualizacion(java.time.LocalDateTime.now());

            usuarioService.guardar(usuario);

            redirectAttributes.addFlashAttribute("exito", "Administrador registrado exitosamente. Por favor inicie sesión.");
            return "redirect:/login";
        } catch (Exception e) {
            e.printStackTrace(); // Muestra el error exacto en la consola si vuelve a fallar
            redirectAttributes.addFlashAttribute("error", "Error al guardar en la BD: " + e.getMessage());
            return "redirect:/registro";
        }
    }
}