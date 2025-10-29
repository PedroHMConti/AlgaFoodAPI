package AlgaFoodAPI.api.controller;


import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Exception.NegocioException;
import AlgaFoodAPI.Domain.Model.FormaDePagamento;
import AlgaFoodAPI.Domain.Repository.FormaDePagamentoRepository;
import AlgaFoodAPI.Domain.service.CadastroFormaDePagamento;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/formas-de-pagamento")
public class FormaDePagamentoController {

    @Autowired
    private FormaDePagamentoRepository repository;

    @Autowired
    private CadastroFormaDePagamento cadastroFormaDePagamento;

    @GetMapping
    public List<?> listar(){
        return repository.findAll();
    }

    @GetMapping("/{formaDePagamentoId}")
    public ResponseEntity<?> buscar(@PathVariable Long formaDePagamentoId){
        try {
            FormaDePagamento formaDePagamento = repository.findById(formaDePagamentoId).orElseThrow(() -> new NegocioException(String.format("não existe cadastro para forma de pagamento com o código %d", formaDePagamentoId)));
            return ResponseEntity.ok(formaDePagamento);
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<FormaDePagamento> adicionar(@RequestBody FormaDePagamento formaDePagamento){
            FormaDePagamento forma = cadastroFormaDePagamento.salvar(formaDePagamento);
            return ResponseEntity.status(HttpStatus.CREATED).body(forma);
    }

    @PutMapping("/{formaDePagamentoId}")
    public ResponseEntity<?> atualizar(@PathVariable Long formaDePagamentoId,@RequestBody FormaDePagamento formaDePagamento){
        try{
            FormaDePagamento formaDePagamentoAtual = repository.findById(formaDePagamentoId).orElseThrow(() -> new NegocioException(String.format("não existe cadastro para forma de pagamento com o código %d", formaDePagamentoId)));
            BeanUtils.copyProperties(formaDePagamento,formaDePagamentoAtual,"id");
            formaDePagamentoAtual = repository.save(formaDePagamentoAtual);
            return ResponseEntity.ok(formaDePagamentoAtual);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{formaDePagamentoId}")
    public ResponseEntity<?> delete(@PathVariable Long formaDePagamentoId){
        try{
            cadastroFormaDePagamento.excluir(formaDePagamentoId);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontradaException e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
