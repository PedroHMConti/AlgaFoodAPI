package AlgaFoodAPI.Domain.Exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {
    public RestauranteNaoEncontradoException(String message) {
        super(message);
    }

    public RestauranteNaoEncontradoException(Long restauranteId){
      this(String.format("não existe cadastro para o restaurante com o código %d",restauranteId));
    }
}
