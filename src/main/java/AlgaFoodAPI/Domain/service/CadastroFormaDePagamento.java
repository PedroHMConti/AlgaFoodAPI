package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.EntidadeEmUsoException;
import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Exception.FormaDePagamentoNaoEncontradoException;
import AlgaFoodAPI.Domain.Exception.NegocioException;
import AlgaFoodAPI.Domain.Model.FormaDePagamento;
import AlgaFoodAPI.Domain.Repository.FormaDePagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CadastroFormaDePagamento {

    public static final String MSG_FORMA_PAGAMENTO_NOT_FOUND = "não existe cadastro para a forma de pagamento com o código %d%n";
    @Autowired
    private FormaDePagamentoRepository formaDePagamentoRepository;

    public FormaDePagamento salvar(FormaDePagamento formaDePagamento){
        return formaDePagamentoRepository.save(formaDePagamento);
    }

    public void excluir(Long formaDePagamentoId){
        try{
            buscarOuFalhar(formaDePagamentoId);
            formaDePagamentoRepository.deleteById(formaDePagamentoId);
            formaDePagamentoRepository.flush();
        }catch (DataIntegrityViolationException e ){
            throw new EntidadeEmUsoException(String.format("não é possivel excluir o metodo de pagamento %d,pois está em uso",formaDePagamentoId));
        }catch (EmptyResultDataAccessException e){
            throw new FormaDePagamentoNaoEncontradoException(formaDePagamentoId);
        }
    }

    public FormaDePagamento buscarOuFalhar(Long formaDePagamentoId){
        return formaDePagamentoRepository.findById(formaDePagamentoId).orElseThrow(()->new FormaDePagamentoNaoEncontradoException(formaDePagamentoId));
    }
}
