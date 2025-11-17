package AlgaFoodAPI.Domain.Exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }
    public ProdutoNaoEncontradoException(Long produtoId){
        super(String.format("Não existe cadastro para o produto com o código %d",produtoId));
    }
}
