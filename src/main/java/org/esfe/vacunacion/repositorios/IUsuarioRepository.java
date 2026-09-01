package org.esfe.vacunacion.repositorios;

import org.esfe.vacunacion.modelos.EstadoUsuario;
import org.esfe.vacunacion.modelos.RolUsuario;
import org.esfe.vacunacion.modelos.Usuario;
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
<<<<<<< HEAD
>>>>>>> feature/VAC-17-CrearCampanasVacunacion
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
=======
>>>>>>> b5bcebd2db8c4a32f750858648503697716b458d
<<<<<<< HEAD
=======
>>>>>>> 9967226b59ff5435e15d6296fb8f4837822cd480
>>>>>>> feature/VAC-17-CrearCampanasVacunacion
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
<<<<<<< HEAD
<<<<<<< HEAD

=======
    
>>>>>>> b5bcebd2db8c4a32f750858648503697716b458d
=======
    
=======
<<<<<<< HEAD

=======
    
>>>>>>> b5bcebd2db8c4a32f750858648503697716b458d
>>>>>>> 9967226b59ff5435e15d6296fb8f4837822cd480
>>>>>>> feature/VAC-17-CrearCampanasVacunacion
    Optional<Usuario> findByCorreo(String correo);

    boolean existsByCorreo(String correo);

<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
<<<<<<< HEAD
>>>>>>> feature/VAC-17-CrearCampanasVacunacion
    List<Usuario> findByRol(RolUsuario rol);

    List<Usuario> findByEstado(EstadoUsuario estado);

    Optional<Usuario> findByCorreoAndEstado(String correo, EstadoUsuario estado);


    Page<Usuario> findByNombreCompletoContainingIgnoreCase(String nombre, Pageable pageable);
=======
<<<<<<< HEAD
=======
>>>>>>> 9967226b59ff5435e15d6296fb8f4837822cd480
>>>>>>> feature/VAC-17-CrearCampanasVacunacion

    List<Usuario> findByRol(RolUsuario rol);


    List<Usuario> findByEstado(EstadoUsuario estado);

    Optional<Usuario> findByCorreoAndEstado(String correo, EstadoUsuario estado);
<<<<<<< HEAD
>>>>>>> b5bcebd2db8c4a32f750858648503697716b458d
=======
<<<<<<< HEAD
=======
>>>>>>> b5bcebd2db8c4a32f750858648503697716b458d
>>>>>>> 9967226b59ff5435e15d6296fb8f4837822cd480
>>>>>>> feature/VAC-17-CrearCampanasVacunacion
}