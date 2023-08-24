package io.estudos.marialuiza.domain.repository;

import io.estudos.marialuiza.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
