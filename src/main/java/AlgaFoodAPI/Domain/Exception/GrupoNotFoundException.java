package AlgaFoodAPI.Domain.Exception;

public class GrupoNotFoundException extends EntidadeNaoEncontradaException {
    public GrupoNotFoundException(String message) {
        super(message);
    }
    public GrupoNotFoundException(Long grupoId){
      this(String.format("não existe cadastro para o grupo com o código %d",grupoId));
    }
}
