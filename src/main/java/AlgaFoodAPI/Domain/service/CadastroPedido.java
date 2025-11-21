package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.EntidadeEmUsoException;
import AlgaFoodAPI.Domain.Exception.NegocioException;
import AlgaFoodAPI.Domain.Exception.PedidoNaoEncontradoException;
import AlgaFoodAPI.Domain.Model.Pedido;
import AlgaFoodAPI.Domain.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CadastroPedido {

    @Autowired
    PedidoRepository pedidoRepository;

    public Pedido salvar(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    public Pedido buscarOuFalhar(Long pedidoId){
        return pedidoRepository.findById(pedidoId).orElseThrow(()->new PedidoNaoEncontradoException(pedidoId));
    }

    public void excluir(Long pedidoId){
        try{
            buscarOuFalhar(pedidoId);
            pedidoRepository.deleteById(pedidoId);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("não é possível excluir o pedido com o código %d, pois está em uso",pedidoId));
        }
    }
}
