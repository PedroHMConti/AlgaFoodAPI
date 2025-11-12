package AlgaFoodAPI.api.controller;


import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Exception.NegocioException;
import AlgaFoodAPI.Domain.Model.FormaDePagamento;
import AlgaFoodAPI.Domain.Repository.FormaDePagamentoRepository;
import AlgaFoodAPI.Domain.service.CadastroFormaDePagamento;
import jakarta.validation.Valid;
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
    @ResponseStatus(HttpStatus.OK)
    public FormaDePagamento buscar(@PathVariable Long formaDePagamentoId){
        return cadastroFormaDePagamento.buscarOuFalhar(formaDePagamentoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaDePagamento adicionar(@Valid @RequestBody FormaDePagamento formaDePagamento){
            return cadastroFormaDePagamento.salvar(formaDePagamento);
    }

    @PutMapping("/{formaDePagamentoId}")
    @ResponseStatus(HttpStatus.OK)
    public FormaDePagamento atualizar(@PathVariable Long formaDePagamentoId,@Valid @RequestBody FormaDePagamento formaDePagamento){
        FormaDePagamento formaDePagamentoAtual = cadastroFormaDePagamento.buscarOuFalhar(formaDePagamentoId);
        BeanUtils.copyProperties(formaDePagamento,formaDePagamentoAtual,"id");
        return cadastroFormaDePagamento.salvar(formaDePagamentoAtual);
    }

    @DeleteMapping("/{formaDePagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long formaDePagamentoId){
        cadastroFormaDePagamento.excluir(formaDePagamentoId);
    }
}
