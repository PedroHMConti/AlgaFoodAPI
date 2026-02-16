package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.NegocioException;
import AlgaFoodAPI.Domain.Model.Pedido;
import AlgaFoodAPI.Domain.Model.StatusPedido;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class FluxoPedido {

    @Autowired
    private EmissaoPedidoService emissaoPedido;

    @Transactional
    public void cancelar(String codigo){
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigo);
        pedido.cancelar();
    }

    @Transactional
    public void confirmar(String codigo){
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigo);
        pedido.confirmar();
    }

    @Transactional
    public void entregar(String codigo){
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigo);
        pedido.entregar();
    }
}
