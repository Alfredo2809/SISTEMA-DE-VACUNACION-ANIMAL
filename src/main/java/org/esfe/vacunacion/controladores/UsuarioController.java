package org.esfe.vacunacion.controladores;

import org.esfe.vacunacion.modelos.EstadoUsuario;
import org.esfe.vacunacion.modelos.RolUsuario;
import org.esfe.vacunacion.modelos.Usuario;
import org.esfe.vacunacion.servicios.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public String listar(Model model,
                         @RequestParam(defaultValue = "0") int pagina,
                         @RequestParam(required = false) String nombre,
                         Pageable pageable) {

        Page<Usuario> usuarios = (nombre != null && !nombre.isBlank())
                ? usuarioService.buscarPorNombrePaginado(nombre, pageable)
                : usuarioService.obtenerTodosPaginado(pageable);

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("nombre", nombre);
        return "usuarios/lista";
    }



    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("usuario", new Usuario());
        cargarSelectoresRolYEstado(model);
        return "usuarios/formulario";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute Usuario usuario, Model model) {
        try {
            usuarioService.guardar(usuario);
            return "redirect:/usuarios";
        } catch (Exception e) {
            model.addAttribute("error", "No se pudo crear el usuario: " + e.getMessage());
            cargarSelectoresRolYEstado(model);
            return "usuarios/formulario";
        }
    }



    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Optional<Usuario> usuarioOpt = usuarioService.obtenerPorId(id);
        if (usuarioOpt.isEmpty()) {
            return "redirect:/usuarios";
        }
        model.addAttribute("usuario", usuarioOpt.get());
        cargarSelectoresRolYEstado(model);
        return "usuarios/formulario";
    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id, @ModelAttribute Usuario usuario, Model model) {
        usuario.setIdUsuario(id);
        try {
            usuarioService.guardar(usuario);
            return "redirect:/usuarios";
        } catch (Exception e) {
            model.addAttribute("error", "No se pudo actualizar el usuario: " + e.getMessage());
            cargarSelectoresRolYEstado(model);
            return "usuarios/formulario";
        }
    }

    // ---------- Cambio de rol ----------

    @PostMapping("/{id}/cambiar-rol")
    public String cambiarRol(@PathVariable Long id,
                             @RequestParam RolUsuario nuevoRol) {
        usuarioService.cambiarRol(id, nuevoRol);
        return "redirect:/usuarios";
    }

    // ---------- Cambio de estado ----------

    @PostMapping("/{id}/cambiar-estado")
    public String cambiarEstado(@PathVariable Long id,
                                @RequestParam EstadoUsuario nuevoEstado) {
        usuarioService.cambiarEstado(id, nuevoEstado);
        return "redirect:/usuarios";
    }



    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        usuarioService.eliminarPorId(id);
        return "redirect:/usuarios";
    }


    @GetMapping("/selector-rol-estado")
    @ResponseBody
    public SelectorRolEstadoDTO obtenerSelectorRolYEstado() {
        return new SelectorRolEstadoDTO(RolUsuario.values(), EstadoUsuario.values());
    }

    private void cargarSelectoresRolYEstado(Model model) {
        model.addAttribute("roles", RolUsuario.values());
        model.addAttribute("estados", EstadoUsuario.values());
    }


    public record SelectorRolEstadoDTO(RolUsuario[] roles, EstadoUsuario[] estados) {}
}