package org.esfe.vacunacion.servicios.interfaces;


import org.esfe.vacunacion.modelos.EstadoUsuario;
import org.esfe.vacunacion.modelos.RolUsuario;
import org.esfe.vacunacion.modelos.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> obtenerTodos();
    Optional<Usuario> obtenerPorId(Long idUsuario);
    Optional<Usuario> obtenerPorCorreo(String correo);
    List<Usuario> obtenerPorRol(RolUsuario rol);
    List<Usuario> obtenerPorEstado(EstadoUsuario estado);
    Usuario crear(Usuario usuario);
    Usuario actualizar(Long idUsuario, Usuario usuario);
    void cambiarEstado(Long idUsuario, EstadoUsuario estado);
    boolean existePorCorreo(String correo);
}