package ar.com.ada.cash.models.response;

import java.util.ArrayList;
import java.util.List;

import ar.com.ada.cash.entities.Compra;

public class UsuarioResponse {
    public String id;
    public String email;
    public String nombre;
    public String apellido;
    public List<Compra> compras = new ArrayList<>();
}
