package org.esfe.vacunacion.repositorios;

import org.esfe.vacunacion.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método para buscar por correo si lo necesitas para autenticación o validaciones
    Optional<Usuario> findByCorreo(String correo);
}