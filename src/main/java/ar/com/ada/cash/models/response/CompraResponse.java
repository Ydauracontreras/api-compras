package ar.com.ada.cash.models.response;

import java.util.*;

import ar.com.ada.cash.entities.Compra;

public class CompraResponse {
    public List<Compra> items = new ArrayList<>();
    public PagingResponse paging = new PagingResponse();
}
