package io.estudos.marialuiza.service;

import io.estudos.marialuiza.domain.entity.Pedido;
import io.estudos.marialuiza.domain.enums.StatusPedido;
import io.estudos.marialuiza.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar (PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
