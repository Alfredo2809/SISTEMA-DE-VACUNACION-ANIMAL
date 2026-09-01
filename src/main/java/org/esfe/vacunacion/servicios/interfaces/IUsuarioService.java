package org.esfe.vacunacion.servicios.interfaces;

import org.esfe.vacunacion.modelos.EstadoUsuario;
import org.esfe.vacunacion.modelos.RolUsuario;
import org.esfe.vacunacion.modelos.Usuario;
<<<<<<< HEAD
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
=======
>>>>>>> b5bcebd2db8c4a32f750858648503697716b458d

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> obtenerTodos();

<<<<<<< HEAD
    Page<Usuario> obtenerTodosPaginado(Pageable pageable);

    Page<Usuario> buscarPorNombrePaginado(String nombre, Pageable pageable);

=======
>>>>>>> b5bcebd2db8c4a32f750858648503697716b458d
    Optional<Usuario> obtenerPorId(Long idUsuario);

    Optional<Usuario> obtenerPorCorreo(String correo);

    List<Usuario> obtenerPorRol(RolUsuario rol);

    Usuario guardar(Usuario usuario);

    void eliminarPorId(Long idUsuario);

<<<<<<< HEAD

=======
    // Métodos específicos solicitados en la tarea
>>>>>>> b5bcebd2db8c4a32f750858648503697716b458d
    Usuario iniciarSesion(String correo, String contrasena);

    Usuario cambiarRol(Long idUsuario, RolUsuario nuevoRol);

    Usuario cambiarEstado(Long idUsuario, EstadoUsuario nuevoEstado);
}