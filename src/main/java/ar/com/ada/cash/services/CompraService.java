package ar.com.ada.cash.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ar.com.ada.cash.entities.Compra;
import ar.com.ada.cash.entities.Usuario;
import ar.com.ada.cash.repositories.CompraRepository;
import ar.com.ada.cash.services.base.GenericService;

@Service
public class CompraService extends GenericService<Compra> {
    @Autowired
    UsuarioService usuarioService;

    private CompraRepository repo() {
        return (CompraRepository) repository;
    }

    public Compra crearCompra(Integer id, BigDecimal total) {
        Usuario usuario = usuarioService.finById(id);
        Compra compra = new Compra();
        compra.setTotal(total);
        compra.setUsuario(usuario);
        usuario.getCompras().add(compra);
        if (create(compra)) {
            return compra;
        } else {
            return null;
        }

    }

    public List<Compra> listComprasByPage(Integer page, Integer size) {
        return this.repo().findAllByOffset((page - 1) * size, size);
    }

    public List<Compra> listLoanByPage(Integer usuarioId, Integer page, Integer size) {

        return this.repo().findAllByOffsetByusuario(usuarioId, (page - 1) * size, size);

    }

    public long countByUsuarioId(Integer usuarioId) {
        return this.repo().countByusuarioId(usuarioId);
    }

    public List<Compra> listCompraByPageV2(Integer page, Integer size) {
        PageRequest paginacion = PageRequest.of(page - 1, size);
        return this.repo().findAll(paginacion).toList();

    }

}
