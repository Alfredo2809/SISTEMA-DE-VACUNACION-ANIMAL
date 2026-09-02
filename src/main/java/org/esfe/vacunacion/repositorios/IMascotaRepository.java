package org.esfe.vacunacion.repositorios;

import org.esfe.vacunacion.modelos.Especie;
import org.esfe.vacunacion.modelos.EstadoMascota;
import org.esfe.vacunacion.modelos.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMascotaRepository extends JpaRepository<Mascota, Long> {
    
    List<Mascota> findByResponsableIdResponsable(Long idResponsable);


    List<Mascota> findByViviendaIdVivienda(Long idVivienda);

    List<Mascota> findByEspecie(Especie especie);


    List<Mascota> findByEstado(EstadoMascota estado);

    List<Mascota> findByNombreContainingIgnoreCase(String nombre);
}