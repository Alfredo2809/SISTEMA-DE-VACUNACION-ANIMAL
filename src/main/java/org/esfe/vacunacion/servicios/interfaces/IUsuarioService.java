package org.esfe.vacunacion.servicios.interfaces;

import org.esfe.vacunacion.modelos.EstadoUsuario;
import org.esfe.vacunacion.modelos.RolUsuario;
import org.esfe.vacunacion.modelos.Usuario;
<<<<<<< HEAD
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
=======
>>>>>>> b5bcebd2db8c4a32f750858648503697716b458d

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
=======
>>>>>>> b5bcebd2db8c4a32f750858648503697716b458d
>>>>>>> 9967226b59ff5435e15d6296fb8f4837822cd480

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> obtenerTodos();

<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
<<<<<<< HEAD
>>>>>>> feature/VAC-17-CrearCampanasVacunacion
    Page<Usuario> obtenerTodosPaginado(Pageable pageable);

    Page<Usuario> buscarPorNombrePaginado(String nombre, Pageable pageable);

=======
>>>>>>> b5bcebd2db8c4a32f750858648503697716b458d
<<<<<<< HEAD
=======
>>>>>>> 9967226b59ff5435e15d6296fb8f4837822cd480
>>>>>>> feature/VAC-17-CrearCampanasVacunacion
    Optional<Usuario> obtenerPorId(Long idUsuario);

    Optional<Usuario> obtenerPorCorreo(String correo);

    List<Usuario> obtenerPorRol(RolUsuario rol);

    Usuario guardar(Usuario usuario);

    void eliminarPorId(Long idUsuario);

<<<<<<< HEAD
<<<<<<< HEAD

=======
    // Métodos específicos solicitados en la tarea
>>>>>>> b5bcebd2db8c4a32f750858648503697716b458d
=======
    // Métodos específicos solicitados en la tarea
=======
<<<<<<< HEAD

=======
    // Métodos específicos solicitados en la tarea
>>>>>>> b5bcebd2db8c4a32f750858648503697716b458d
>>>>>>> 9967226b59ff5435e15d6296fb8f4837822cd480
>>>>>>> feature/VAC-17-CrearCampanasVacunacion
    Usuario iniciarSesion(String correo, String contrasena);

    Usuario cambiarRol(Long idUsuario, RolUsuario nuevoRol);

    Usuario cambiarEstado(Long idUsuario, EstadoUsuario nuevoEstado);
}