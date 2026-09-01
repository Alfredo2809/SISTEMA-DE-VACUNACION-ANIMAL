package org.esfe.vacunacion.servicios.interfaces;

import org.esfe.vacunacion.modelos.EstadoUsuario;
import org.esfe.vacunacion.modelos.RolUsuario;
import org.esfe.vacunacion.modelos.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> obtenerTodos();

    Page<Usuario> obtenerTodosPaginado(Pageable pageable);

    Page<Usuario> buscarPorNombrePaginado(String nombre, Pageable pageable);

    Optional<Usuario> obtenerPorId(Long idUsuario);

    Optional<Usuario> obtenerPorCorreo(String correo);

    List<Usuario> obtenerPorRol(RolUsuario rol);

    Usuario guardar(Usuario usuario);

    void eliminarPorId(Long idUsuario);


    Usuario iniciarSesion(String correo, String contrasena);

    Usuario cambiarRol(Long idUsuario, RolUsuario nuevoRol);

    Usuario cambiarEstado(Long idUsuario, EstadoUsuario nuevoEstado);
}