package AlgaFoodAPI.Domain.Exception;

import AlgaFoodAPI.Domain.Model.FormaDePagamento;

public class FormaDePagamentoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public FormaDePagamentoNaoEncontradoException(String message) {super(message);}

    public FormaDePagamentoNaoEncontradoException(Long formaDePagamentoId){
        this(String.format("não existe cadastro para a forma de pagamento com o código %d%n",formaDePagamentoId));
    }
}
