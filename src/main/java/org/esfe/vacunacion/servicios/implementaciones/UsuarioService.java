package org.esfe.vacunacion.servicios.implementaciones;


import org.esfe.vacunacion.servicios.interfaces.IUsuarioService;

import org.esfe.vacunacion.modelos.EstadoUsuario;
import org.esfe.vacunacion.modelos.RolUsuario;
import org.esfe.vacunacion.modelos.Usuario;
import org.esfe.vacunacion.repositorios.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> obtenerPorId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    @Override
    public Optional<Usuario> obtenerPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    public List<Usuario> obtenerPorRol(RolUsuario rol) {
        return usuarioRepository.findByRol(rol);
    }

    @Override
    public List<Usuario> obtenerPorEstado(EstadoUsuario estado) {
        return usuarioRepository.findByEstado(estado);
    }

    @Override
    public Usuario crear(Usuario usuario) {
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new IllegalArgumentException("El correo ya se encuentra registrado.");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizar(Long idUsuario, Usuario usuarioDetalles) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));

        usuario.setNombreCompleto(usuarioDetalles.getNombreCompleto());
        usuario.setRol(usuarioDetalles.getRol());
        usuario.setEstado(usuarioDetalles.getEstado());

        if (usuarioDetalles.getContrasena() != null && !usuarioDetalles.getContrasena().isEmpty()) {
            usuario.setContrasena(usuarioDetalles.getContrasena());
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public void cambiarEstado(Long idUsuario, EstadoUsuario nuevoEstado) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));
        usuario.setEstado(nuevoEstado);
        usuarioRepository.save(usuario);
    }

    @Override
    public boolean existePorCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }
}