package org.esfe.vacunacion.repositorios;

import org.esfe.vacunacion.modelos.EstadoUsuario;
import org.esfe.vacunacion.modelos.RolUsuario;
import org.esfe.vacunacion.modelos.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreo(String correo);

    boolean existsByCorreo(String correo);

    List<Usuario> findByRol(RolUsuario rol);

    List<Usuario> findByEstado(EstadoUsuario estado);

    Optional<Usuario> findByCorreoAndEstado(String correo, EstadoUsuario estado);

    Page<Usuario> findByNombreCompletoContainingIgnoreCase(String nombre, Pageable pageable);
}