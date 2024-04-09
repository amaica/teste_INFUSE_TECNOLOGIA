package com.teste.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teste.model.Pedido;
import com.teste.service.PedidoService;

@RestController
@RequestMapping("/pedidos")   
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@PostMapping(value = "/receber", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> receberPedidos(@RequestBody List<Pedido> pedidos) {
		for (Pedido pedido : pedidos) {
			pedidoService.receberPedido(pedido);
		}
		return new ResponseEntity<>("Pedidos recebidos com sucesso", HttpStatus.CREATED);
	}

	@GetMapping("/consultar")
	public ResponseEntity<List<Pedido>> consultarPedidos(@RequestParam(required = false) Integer numeroControle,
			@RequestParam(required = false) String dataCadastro) {

		LocalDate dataCadastroParsed = null;
		if (dataCadastro != null) {
			dataCadastroParsed = LocalDate.parse(dataCadastro);
		}
		List<Pedido> pedidos = pedidoService.consultarPedidos(numeroControle, dataCadastroParsed);
		if (pedidos != null && !pedidos.isEmpty()) {
			return new ResponseEntity<>(pedidos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
