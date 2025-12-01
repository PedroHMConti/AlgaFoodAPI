package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.PedidoNaoEncontradoException;
import AlgaFoodAPI.Domain.Model.Pedido;
import AlgaFoodAPI.Domain.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmissaoPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido buscarOuFalhar(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
    }

}
