package org.esfe.vacunacion.controladores;

import org.esfe.vacunacion.modelos.Municipio;
import org.esfe.vacunacion.servicios.interfaces.IDepartamentoService;
import org.esfe.vacunacion.servicios.interfaces.IMunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/municipios")
public class MunicipioController {

    @Autowired
    private IMunicipioService municipioService;

    @Autowired
    private IDepartamentoService departamentoService;


    @GetMapping
    public String listar(Model model) {
        model.addAttribute("municipios", municipioService.obtenerTodos());
        return "geografico/municipios/lista";
    }

    @GetMapping("/crear")
    public String formularioCrear(Model model) {
        model.addAttribute("municipio", new Municipio());
        model.addAttribute("departamentos", departamentoService.obtenerTodos());
        model.addAttribute("titulo", "Nuevo Municipio");
        return "geografico/municipios/formulario";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Municipio> municipio = municipioService.obtenerPorId(id);
        if (municipio.isPresent()) {
            model.addAttribute("municipio", municipio.get());
            model.addAttribute("departamentos", departamentoService.obtenerTodos());
            model.addAttribute("titulo", "Editar Municipio");
            return "geografico/municipios/formulario";
        }
        redirectAttributes.addFlashAttribute("mensajeError", "El municipio especificado no existe.");
        return "redirect:/municipios";
    }

    @PostMapping
    public String guardar(@ModelAttribute Municipio municipio, RedirectAttributes redirectAttributes) {
        try {
            municipioService.guardar(municipio);
            redirectAttributes.addFlashAttribute("mensajeExito", "Municipio guardado exitosamente");
            return "redirect:/municipios";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
            if (municipio.getIdMunicipio() != null) {
                return "redirect:/municipios/editar/" + municipio.getIdMunicipio();
            }
            return "redirect:/municipios/crear";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error interno al procesar el municipio.");
            return "redirect:/municipios";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            municipioService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensajeExito", "Municipio eliminado correctamente");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se puede eliminar el municipio debido a registros relacionados.");
        }
        return "redirect:/municipios";
    }


    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Municipio>> obtenerTodosAPI() {
        return ResponseEntity.ok(municipioService.obtenerTodos());
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Municipio> obtenerPorIdAPI(@PathVariable Long id) {
        return municipioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Municipio> crearAPI(@RequestBody Municipio municipio) {
        try {
            Municipio guardado = municipioService.guardar(municipio);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> eliminarAPI(@PathVariable Long id) {
        try {
            municipioService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}