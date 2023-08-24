package io.estudos.marialuiza.domain.repository;

import io.estudos.marialuiza.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedido extends JpaRepository<ItemPedido, Integer> {
}
