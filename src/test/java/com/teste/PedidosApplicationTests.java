package com.teste;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

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
    void testReceberPedido_Success() {
        Pedido pedido = new Pedido();
        pedido.setNumeroControle(123);
        pedido.setDataCadastro(LocalDate.now());
        pedido.setNome("Produto Teste");
        pedido.setValor(10.0);
        pedido.setQuantidade(2);
        pedido.setCodigoCliente(1);

        doNothing().when(pedidoService).receberPedido(pedido);

        ResponseEntity<String> response = pedidoController.receberPedido(pedido);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Pedido recebido com sucesso", response.getBody());

        verify(pedidoService, times(1)).receberPedido(pedido);
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

        ResponseEntity<String> response = pedidoController.receberPedido(pedido);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro no pedido", response.getBody());

        verify(pedidoService, times(1)).receberPedido(pedido);
    }
}
