package AlgaFoodAPI.Domain.Exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {
    public CidadeNaoEncontradaException(String message) {
        super(message);
    }

    public CidadeNaoEncontradaException(Long cidadeId){
        this(String.format("não existe cadastro para cidade com o código %d",cidadeId));
    }
}
