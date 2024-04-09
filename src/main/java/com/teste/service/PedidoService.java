package com.teste.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.model.Pedido;
import com.teste.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public void receberPedido(Pedido pedido) {

		Pedido existingPedido = pedidoRepository.getByNumeroControle(pedido.getNumeroControle());
		if (existingPedido != null) {
			throw new IllegalArgumentException("Número de controle já cadastrado.");
		}

		if (pedido.getDataCadastro() == null) {
			pedido.setDataCadastro(LocalDate.now());
		}

		if (pedido.getQuantidade() == null) {
			pedido.setQuantidade(1);
		}

		if (pedido.getQuantidade() >= 10) {
			pedido.setValorTotal(pedido.getValor() * pedido.getQuantidade() * 0.9);
		} else if (pedido.getQuantidade() > 5) {
			pedido.setValorTotal(pedido.getValor() * pedido.getQuantidade() * 0.95);
		} else {
			pedido.setValorTotal(pedido.getValor() * pedido.getQuantidade());
		}

		pedidoRepository.save(pedido);
	}

	public List<Pedido> consultarPedidos(Integer numeroControle, LocalDate dataCadastro) {
		if (numeroControle != null && dataCadastro != null) {
			return pedidoRepository.findByNumeroControleAndDataCadastro(numeroControle, dataCadastro);
		} else if (numeroControle != null) {
			return pedidoRepository.findByNumeroControle(numeroControle);
		} else if (dataCadastro != null) {
			return pedidoRepository.findByDataCadastro(dataCadastro);
		} else {
			return pedidoRepository.findAll();
		}
	}
}
