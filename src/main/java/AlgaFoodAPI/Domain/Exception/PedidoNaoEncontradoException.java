package AlgaFoodAPI.Domain.Exception;

public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException(String message) {
        super(message);
    }
    public PedidoNaoEncontradoException(Long pedidoId){
        super(String.format("não existe cadastro para o pedido com o código %d",pedidoId));
    }

}
