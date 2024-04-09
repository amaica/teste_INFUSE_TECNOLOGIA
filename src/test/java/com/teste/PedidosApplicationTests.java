package com.teste;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.teste.controller.PedidoController;
import com.teste.model.Pedido;
import com.teste.service.PedidoService;

@ExtendWith(MockitoExtension.class)
class PedidoControllerTest {

    @Mock
    private PedidoService pedidoService;

    @InjectMocks	
    private PedidoController pedidoController;
    @Test
    void testReceberPedidos_Success() {
      
        Pedido pedido1 = new Pedido();
        pedido1.setNumeroControle(123);
        pedido1.setDataCadastro(LocalDate.now());
        pedido1.setNome("Produto Teste 1");
        pedido1.setValor(10.0);
        pedido1.setQuantidade(2);
        pedido1.setCodigoCliente(1);

        Pedido pedido2 = new Pedido();
        pedido2.setNumeroControle(456);
        pedido2.setDataCadastro(LocalDate.now());
        pedido2.setNome("Produto Teste 2");
        pedido2.setValor(15.0);
        pedido2.setQuantidade(1);
        pedido2.setCodigoCliente(2);

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido1);
        pedidos.add(pedido2);

        doNothing().when(pedidoService).receberPedido(pedido1);
        doNothing().when(pedidoService).receberPedido(pedido2);

     
        ResponseEntity<String> response = pedidoController.receberPedidos(pedidos);

      
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Pedidos recebidos com sucesso", response.getBody());

        verify(pedidoService, times(1)).receberPedido(pedido1);
        verify(pedidoService, times(1)).receberPedido(pedido2);
    }

    @Test
    void testReceberPedido_Failure() {
      
        Pedido pedido = new Pedido();
        pedido.setNumeroControle(123);
        pedido.setDataCadastro(LocalDate.now());
        pedido.setNome("Produto Teste");
        pedido.setValor(10.0);
        pedido.setQuantidade(2);
        pedido.setCodigoCliente(1);

        IllegalArgumentException exception = new IllegalArgumentException("Erro no pedido");
        doThrow(exception).when(pedidoService).receberPedido(pedido);
        ResponseEntity<String> response = pedidoController.receberPedidos(Collections.singletonList(pedido));

    
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro no pedido", response.getBody());

        verify(pedidoService, times(1)).receberPedido(pedido);
    }
}
