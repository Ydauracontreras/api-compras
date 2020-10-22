package ar.com.ada.cash.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ar.com.ada.cash.entities.Compra;
import ar.com.ada.cash.models.request.CompraRequest;
import ar.com.ada.cash.models.response.CompraResponse;
import ar.com.ada.cash.models.response.GenericResponse;
import ar.com.ada.cash.services.CompraService;

@RestController
public class CompraController {
    @Autowired
    CompraService compraService;

    @PostMapping("/compras")
    public ResponseEntity<?> registarLoan(@RequestBody CompraRequest compraRequest) {
        GenericResponse r = new GenericResponse();
        Compra compra = compraService.crearCompra(compraRequest.idUsuario, compraRequest.total);
        if (compra != null) {
            r.isOk = true;
            r.id = compra.getCompraId();
            r.message = "Registrates con exito una compra";
            return ResponseEntity.ok().body(r);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/compras")
    public ResponseEntity<CompraResponse> getCompras(@RequestParam(value = "page", required = true) Integer page,
            @RequestParam(value = "size", required = true) Integer size,
            @RequestParam(value = "user_id", required = false) Integer usuarioId) {

        CompraResponse info = new CompraResponse();

        List<Compra> compras = new ArrayList<>();
        if (usuarioId == null) {
            compras = compraService.listCompraByPageV2(page, size);
            info.paging.total = compraService.count();
        } else {
            compras = compraService.listLoanByPage(usuarioId, page, size);
            info.paging.total = compraService.countByUsuarioId(usuarioId);

            compraService.countByUsuarioId(usuarioId);
        }
        info.items = compras;
        info.paging.page = page;
        info.paging.size = size;

        return ResponseEntity.ok(info);
    }

}