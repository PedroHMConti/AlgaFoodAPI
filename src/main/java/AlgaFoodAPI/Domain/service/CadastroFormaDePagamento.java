package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Model.FormaDePagamento;
import AlgaFoodAPI.Domain.Repository.FormaDePagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class CadastroFormaDePagamento {

    @Autowired
    private FormaDePagamentoRepository repository;

    public ResponseEntity<?> salvar(FormaDePagamento formaDePagamento){
        try{
            repository.adicionar(formaDePagamento);
            return ResponseEntity.ok(formaDePagamento);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
