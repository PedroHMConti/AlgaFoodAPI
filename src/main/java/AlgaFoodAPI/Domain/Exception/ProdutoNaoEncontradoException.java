package AlgaFoodAPI.Domain.Exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }
    public ProdutoNaoEncontradoException(Long produtoId){
        super(String.format("Não existe cadastro para o produto com o código %d",produtoId));
    }
    public ProdutoNaoEncontradoException(Long produtoId,Long restauranteId){
        this(String.format("Não existe um cadastro de produto com código %d para o restaurante de código %d",
                produtoId, restauranteId));
    }
}
