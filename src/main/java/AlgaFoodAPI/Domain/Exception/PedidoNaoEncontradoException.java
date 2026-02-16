package AlgaFoodAPI.Domain.Exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public PedidoNaoEncontradoException(String codigo){
        super(String.format("não existe cadastro para o pedido com o código %s",codigo));
    }

}
