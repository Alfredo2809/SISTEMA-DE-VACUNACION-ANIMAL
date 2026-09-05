package org.esfe.vacunacion.controladores;

import org.esfe.vacunacion.modelos.Municipio;
import org.esfe.vacunacion.servicios.interfaces.IDepartamentoService;
import org.esfe.vacunacion.servicios.interfaces.IMunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/geografico/municipios")
public class MunicipioController {

    @Autowired
    private IMunicipioService municipioService;

    @Autowired
    private IDepartamentoService departamentoService;

    @GetMapping
    public String listar(@RequestParam(defaultValue = "0") int page, Model model) {

        Page<Municipio> municipioPage = municipioService.obtenerPaginados(PageRequest.of(page, 4));

        model.addAttribute("municipios", municipioPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", municipioPage.getTotalPages());
        model.addAttribute("totalItems", municipioPage.getTotalElements());

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
        return "redirect:/geografico/municipios";
    }

    @GetMapping("/detalles/{id}")
    public String detalles(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Municipio> municipio = municipioService.obtenerPorId(id);
        if (municipio.isPresent()) {
            model.addAttribute("municipio", municipio.get());
            return "geografico/municipios/detalles";
        }
        redirectAttributes.addFlashAttribute("mensajeError", "El municipio especificado no existe.");
        return "redirect:/geografico/municipios";
    }

    @PostMapping
    public String guardar(@ModelAttribute Municipio municipio, Model model, RedirectAttributes redirectAttributes) {
        try {
            municipioService.guardar(municipio);
            redirectAttributes.addFlashAttribute("mensajeExito", "Municipio guardado exitosamente");
            return "redirect:/geografico/municipios";
        } catch (IllegalArgumentException e) {
            model.addAttribute("mensajeError", e.getMessage());
            model.addAttribute("municipio", municipio);
            model.addAttribute("departamentos", departamentoService.obtenerTodos());
            model.addAttribute("titulo", municipio.getIdMunicipio() != null ? "Editar Municipio" : "Nuevo Municipio");
            return "geografico/municipios/formulario";
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error interno al procesar el municipio.");
            model.addAttribute("municipio", municipio);
            model.addAttribute("departamentos", departamentoService.obtenerTodos());
            model.addAttribute("titulo", municipio.getIdMunicipio() != null ? "Editar Municipio" : "Nuevo Municipio");
            return "geografico/municipios/formulario";
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
        return "redirect:/geografico/municipios";
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
    public ResponseEntity<?> crearAPI(@RequestBody Municipio municipio) {
        try {
            Municipio guardado = municipioService.guardar(municipio);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
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