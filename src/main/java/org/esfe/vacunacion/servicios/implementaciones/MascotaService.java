package org.esfe.vacunacion.servicios.implementaciones;

import org.esfe.vacunacion.modelos.Especie;
import org.esfe.vacunacion.modelos.EstadoMascota;
import org.esfe.vacunacion.modelos.Mascota;
import org.esfe.vacunacion.repositorios.IMascotaRepository;
import org.esfe.vacunacion.servicios.interfaces.IMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService implements IMascotaService {

    @Autowired
    private IMascotaRepository mascotaRepository;

    @Override
    public List<Mascota> obtenerTodas() {
        return mascotaRepository.findAll();
    }

    @Override
    public Optional<Mascota> obtenerPorId(Long idMascota) {
        return mascotaRepository.findById(idMascota);
    }

    @Override
    public Mascota guardar(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    public void eliminarPorId(Long idMascota) {
        mascotaRepository.deleteById(idMascota);
    }

    @Override
    public Mascota actualizarInformacion(Long idMascota, String nombre, Especie especie, Integer edadAproximadaMeses) {
        Mascota mascota = mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + idMascota));

        mascota.setNombre(nombre);
        mascota.setEspecie(especie);
        mascota.setEdadAproximadaMeses(edadAproximadaMeses);

        return mascotaRepository.save(mascota);
    }

    @Override
    public Mascota cambiarEstado(Long idMascota, EstadoMascota nuevoEstado) {
        Mascota mascota = mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + idMascota));

        mascota.setEstado(nuevoEstado);

        return mascotaRepository.save(mascota);
    }
}