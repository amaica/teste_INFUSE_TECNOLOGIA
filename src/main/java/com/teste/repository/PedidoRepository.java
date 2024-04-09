package com.teste.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	Pedido getByNumeroControle(Integer numeroControle);

	List<Pedido> findByNumeroControle(Integer numeroControle);

	List<Pedido> findByNumeroControleAndDataCadastro(Integer numeroPedido, LocalDate dataCadastro);

	List<Pedido> findByDataCadastro(LocalDate dataCadastro);

}
