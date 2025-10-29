package AlgaFoodAPI.Domain.Exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {
    public CozinhaNaoEncontradaException(String message) {
        super(message);
    }

    public CozinhaNaoEncontradaException(Long cozinhaId){
      this(String.format("não existe cadastro  para cozinha com código %d",cozinhaId));
    }
}
