package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Model.FormaDePagamento;
import AlgaFoodAPI.Domain.Repository.FormaDePagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CadastroFormaDePagamento {

    @Autowired
    private FormaDePagamentoRepository repository;

    public FormaDePagamento salvar(FormaDePagamento formaDePagamento){
        return repository.save(formaDePagamento);
    }

    public void excluir(Long formaDePagamentoId){
        FormaDePagamento forma = repository.findById(formaDePagamentoId).orElseThrow(() ->new EntidadeNaoEncontradaException(String.format("não existe cadastro para forma de pagamento com o código %d",formaDePagamentoId)));
        repository.delete(forma);
    }
}
