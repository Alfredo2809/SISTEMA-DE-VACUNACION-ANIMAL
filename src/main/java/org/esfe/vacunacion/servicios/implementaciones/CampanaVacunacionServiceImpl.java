package org.esfe.vacunacion.servicios.implementaciones;

import org.esfe.vacunacion.modelos.CampanaVacunacion;
import org.esfe.vacunacion.repositorios.CampanaVacunacionRepository;
import org.esfe.vacunacion.servicios.interfaces.ICampanaVacunacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampanaVacunacionServiceImpl implements ICampanaVacunacionService {

    @Autowired
    private CampanaVacunacionRepository campanaVacunacionRepository;

    @Override
    public List<CampanaVacunacion> listar() {
        return campanaVacunacionRepository.findAll();
    }

    @Override
    public Optional<CampanaVacunacion> buscarPorId(Long id) {
        return campanaVacunacionRepository.findById(id);
    }

    @Override
    public CampanaVacunacion guardar(CampanaVacunacion campana) {
        validarRangoFechas(campana);
        return campanaVacunacionRepository.save(campana);
    }

    @Override
    public void eliminar(Long id) {
        campanaVacunacionRepository.deleteById(id);
    }

    private void validarRangoFechas(CampanaVacunacion campana) {
        if (campana.getFechaInicio() == null || campana.getFechaFin() == null) {
            throw new IllegalArgumentException("La fecha de inicio y la fecha de fin son obligatorias.");
        }
        if (!campana.getFechaFin().isAfter(campana.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior a la fecha de inicio.");
        }
    }
}
