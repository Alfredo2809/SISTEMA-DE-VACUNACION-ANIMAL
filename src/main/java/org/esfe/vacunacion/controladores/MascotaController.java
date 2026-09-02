package org.esfe.vacunacion.controladores;

import org.esfe.vacunacion.modelos.Especie;
import org.esfe.vacunacion.modelos.EstadoMascota;
import org.esfe.vacunacion.modelos.Mascota;
import org.esfe.vacunacion.modelos.Responsable;
import org.esfe.vacunacion.modelos.Vivienda;
import org.esfe.vacunacion.servicios.interfaces.IMascotaService;
import org.esfe.vacunacion.servicios.interfaces.IResponsableService;
import org.esfe.vacunacion.servicios.interfaces.IViviendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private IMascotaService mascotaService;

    @Autowired
    private IResponsableService responsableService;

    @Autowired
    private IViviendaService viviendaService;

    // ---------- Listado ----------

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("mascotas", mascotaService.obtenerTodas());
        cargarSelectoresEspecieYEstado(model);
        return "mascotas/lista";
    }

    // ---------- Creación ----------

    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        Mascota mascota = new Mascota();
        // Placeholders para evitar NPE al enlazar responsable.idResponsable / vivienda.idVivienda en el form
        mascota.setResponsable(new Responsable());
        mascota.setVivienda(new Vivienda());
        model.addAttribute("mascota", mascota);
        cargarDatosFormulario(model);
        return "mascotas/formulario";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute Mascota mascota, Model model) {
        try {
            mascotaService.guardar(mascota);
            return "redirect:/mascotas";
        } catch (Exception e) {
            model.addAttribute("error", "No se pudo crear la mascota: " + e.getMessage());
            cargarDatosFormulario(model);
            return "mascotas/formulario";
        }
    }

    // ---------- Edición ----------

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Optional<Mascota> mascotaOpt = mascotaService.obtenerPorId(id);
        if (mascotaOpt.isEmpty()) {
            return "redirect:/mascotas";
        }
        model.addAttribute("mascota", mascotaOpt.get());
        cargarDatosFormulario(model);
        return "mascotas/formulario";
    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id, @ModelAttribute Mascota mascota, Model model) {
        mascota.setIdMascota(id);
        try {
            mascotaService.guardar(mascota);
            return "redirect:/mascotas";
        } catch (Exception e) {
            model.addAttribute("error", "No se pudo actualizar la mascota: " + e.getMessage());
            cargarDatosFormulario(model);
            return "mascotas/formulario";
        }
    }

    // ---------- Cambio rápido de estado (acción independiente, desde la tabla) ----------

    @PostMapping("/{id}/cambiar-estado")
    public String cambiarEstado(@PathVariable Long id, @RequestParam EstadoMascota nuevoEstado) {
        mascotaService.cambiarEstado(id, nuevoEstado);
        return "redirect:/mascotas";
    }

    // ---------- Eliminar ----------

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        mascotaService.eliminarPorId(id);
        return "redirect:/mascotas";
    }



    private void cargarDatosFormulario(Model model) {
        model.addAttribute("responsables", responsableService.listarTodos());
        model.addAttribute("viviendas", viviendaService.listarTodas());
        cargarSelectoresEspecieYEstado(model);
    }

    private void cargarSelectoresEspecieYEstado(Model model) {
        model.addAttribute("especies", Especie.values());
        model.addAttribute("estados", EstadoMascota.values());
    }
}