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

    public Pedido buscarOuFalhar(String codigo){
        return pedidoRepository.findByCodigo(codigo).orElseThrow(()->new PedidoNaoEncontradoException(codigo));
    }

    public void excluir(String codigo){
        try{
            buscarOuFalhar(codigo);
            pedidoRepository.deleteByCodigo(codigo);
            pedidoRepository.flush();
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("não é possível excluir o pedido com o código %s, pois está em uso",codigo));
        }
    }
}
