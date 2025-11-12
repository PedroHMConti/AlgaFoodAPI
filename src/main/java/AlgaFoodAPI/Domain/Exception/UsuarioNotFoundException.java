package AlgaFoodAPI.Domain.Exception;

public class UsuarioNotFoundException extends EntidadeNaoEncontradaException{
    public UsuarioNotFoundException(String message){
        super(message);
    }
    public UsuarioNotFoundException(Long usuarioId){
        this(String.format("não existe cadastro para o usuário com o código %d",usuarioId));
    }
}
