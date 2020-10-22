package ar.com.ada.cash.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.cash.entities.*;
import ar.com.ada.cash.models.request.*;
import ar.com.ada.cash.models.response.*;
import ar.com.ada.cash.services.*;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/usuarios")
    public ResponseEntity<?> registrarUser(@RequestBody UsuarioRequest usuarioR) {
        GenericResponse r = new GenericResponse();
        Usuario usuario = usuarioService.crearUser(usuarioR.email, usuarioR.nombre, usuarioR.apellido);
        if (usuario != null) {
            r.isOk = true;
            r.id = usuario.getUsuarioId();
            r.message = "Usuario agregado con exito";
            return ResponseEntity.ok().body(r);
        } else {
            return ResponseEntity.badRequest().build();

        }

    }

    @GetMapping("/usuarios")
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(usuarioService.findAll());

    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> obtenerUsuarioporId(@PathVariable Integer id) {
        Usuario usuario = usuarioService.finById(id);
        if (usuario == null) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(usuario);

    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id) {
        GenericResponse r = new GenericResponse();
        Usuario usuario = usuarioService.finById(id);
        boolean resultado = usuarioService.delete(usuario);
        if (resultado) {
            r.id = usuario.getUsuarioId();
            r.isOk = true;
            r.message = "Usuario liminado con exito";
            return ResponseEntity.ok().body(r);
        }
        return ResponseEntity.badRequest().build();

    }

}
