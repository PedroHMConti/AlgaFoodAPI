package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.NegocioException;
import AlgaFoodAPI.Domain.Exception.PedidoNaoEncontradoException;
import AlgaFoodAPI.Domain.Model.*;
import AlgaFoodAPI.Domain.Repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmissaoPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CadastroRestaurante cadastroRestaurante;

    @Autowired
    private CadastroCidade cadastroCidade;

    @Autowired
    private CadastroUsuario cadastroUsuario;

    @Autowired
    private CadastroProduto cadastroProduto;

    @Autowired
    private CadastroFormaDePagamento cadastroFormaPagamento;

    @Transactional
    public Pedido emitir(Pedido pedido) {
        validarPedido(pedido);
        validarItens(pedido);

        pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
        pedido.calcularValorTotal();

        return pedidoRepository.save(pedido);
    }
    @Transactional
    private void validarPedido(Pedido pedido) {
        try{
            Cidade cidade = cadastroCidade.buscarOuFalhar(pedido.getEnderecoEntrega().getCidade().getId());
            Usuario cliente = cadastroUsuario.buscarOuFalhar(pedido.getCliente().getId());
            Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(pedido.getRestaurante().getId());
            FormaDePagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(pedido.getFormaPagamento().getId());

            pedido.getEnderecoEntrega().setCidade(cidade);
            pedido.setCliente(cliente);
            pedido.setRestaurante(restaurante);
            pedido.setFormaPagamento(formaPagamento);

            if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
                throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
                        formaPagamento.getDescricao()));
            }

        }catch (Exception e){
            throw new NegocioException(e.getMessage());
        }
    }
    @Transactional
    private void validarItens(Pedido pedido) {
        pedido.getItens().forEach(item -> {
            Produto produto = cadastroProduto.buscarOuFalhar(
                    pedido.getRestaurante().getId(), item.getProduto().getId());

            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
        });
    }


    public Pedido buscarOuFalhar(String codigo) {
        return pedidoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new PedidoNaoEncontradoException(codigo));
    }


}
