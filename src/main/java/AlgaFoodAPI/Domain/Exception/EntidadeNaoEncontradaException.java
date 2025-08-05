package AlgaFoodAPI.Domain.Exception;

public class EntidadeNaoEncontradaException extends RuntimeException{
    public EntidadeNaoEncontradaException(String mensagem){
        super(mensagem);
    }
}
