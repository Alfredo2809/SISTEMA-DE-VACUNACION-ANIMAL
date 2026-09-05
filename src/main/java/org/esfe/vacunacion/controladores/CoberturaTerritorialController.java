package org.esfe.vacunacion.controladores;

import jakarta.validation.Valid;
import org.esfe.vacunacion.modelos.CoberturaTerritorial;
import org.esfe.vacunacion.servicios.interfaces.ICampanaVacunacionService;
import org.esfe.vacunacion.servicios.interfaces.ICoberturaTerritorialService;
import org.esfe.vacunacion.servicios.interfaces.IColoniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cobertura-territorial")
public class CoberturaTerritorialController {

    @Autowired
    private ICoberturaTerritorialService coberturaTerritorialService;

    @Autowired
    private ICampanaVacunacionService campanaVacunacionService;

    @Autowired
    private IColoniaService coloniaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("coberturas", coberturaTerritorialService.listarTodas());
        return "cobertura-territorial/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("titulo", "Registrar Cobertura Territorial");
        model.addAttribute("cobertura", new CoberturaTerritorial());
        model.addAttribute("campanas", campanaVacunacionService.listar());
        model.addAttribute("colonias", coloniaService.obtenerTodos());
        return "cobertura-territorial/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        CoberturaTerritorial cobertura = coberturaTerritorialService.buscarPorId(id);
        if (cobertura == null) {
            redirectAttributes.addFlashAttribute("mensajeError", "Cobertura territorial no encontrada.");
            return "redirect:/cobertura-territorial";
        }
        model.addAttribute("titulo", "Editar Cobertura Territorial");
        model.addAttribute("cobertura", cobertura);
        model.addAttribute("campanas", campanaVacunacionService.listar());
        model.addAttribute("colonias", coloniaService.obtenerTodos());
        return "cobertura-territorial/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("cobertura") CoberturaTerritorial cobertura,
                          BindingResult result,
                          Model model,
                          RedirectAttributes redirectAttributes) {

        // 1. Validar si ya existe la asignación Campaña-Colonia (solo para nuevos registros)
        if (cobertura.getIdCobertura() == null &&
                cobertura.getCampana() != null && cobertura.getCampana().getIdCampana() != null &&
                cobertura.getColonia() != null && cobertura.getColonia().getIdColonia() != null) {

            boolean duplicado = coberturaTerritorialService.existeAsignacion(
                    cobertura.getCampana().getIdCampana(),
                    cobertura.getColonia().getIdColonia()
            );

            if (duplicado) {
                result.rejectValue("colonia.idColonia", "error.cobertura", "Esta colonia ya se encuentra asignada a la campaña seleccionada.");
            }
        }

        // 2. Si hay errores de validación, RECARGAR los listados en el Model
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Registrar Cobertura Territorial");
            model.addAttribute("campanas", campanaVacunacionService.listar());
            model.addAttribute("colonias", coloniaService.obtenerTodos());
            return "cobertura-territorial/formulario";
        }

        // 3. RECUPERAR LAS ENTIDADES PERSISTIDAS (Resuelve el TransientPropertyValueException)
        if (cobertura.getCampana() != null && cobertura.getCampana().getIdCampana() != null) {
            campanaVacunacionService.buscarPorId(cobertura.getCampana().getIdCampana())
                    .ifPresent(cobertura::setCampana);
        }

        if (cobertura.getColonia() != null && cobertura.getColonia().getIdColonia() != null) {
            coloniaService.obtenerPorId(cobertura.getColonia().getIdColonia())
                    .ifPresent(cobertura::setColonia);
        }

        // 4. Guardar si todo está correcto
        coberturaTerritorialService.guardar(cobertura);
        redirectAttributes.addFlashAttribute("mensajeExito", "Cobertura territorial registrada correctamente.");
        return "redirect:/cobertura-territorial";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        coberturaTerritorialService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensajeExito", "Cobertura territorial eliminada correctamente.");
        return "redirect:/cobertura-territorial";
    }
}