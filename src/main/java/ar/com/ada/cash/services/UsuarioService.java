package ar.com.ada.cash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.cash.entities.Usuario;
import ar.com.ada.cash.repositories.UsuarioRepository;
import ar.com.ada.cash.services.base.GenericService;

@Service
public class UsuarioService extends GenericService<Usuario> {

    @Autowired
    UsuarioService userService;

    private UsuarioRepository repo() {
        return (UsuarioRepository) repository;
    }

    public boolean exists(String email) {
        return (this.repo().findByEmail(email) != null);
    }

    public Usuario crearUser(String email, String nombre, String apellido) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        if (!exists(email)) {
            this.create(usuario);
            return usuario;
        } else {
            return null;
        }
    }

}
