package AlgaFoodAPI.api.controller;


import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Exception.NegocioException;
import AlgaFoodAPI.Domain.Model.FormaDePagamento;
import AlgaFoodAPI.Domain.Repository.FormaDePagamentoRepository;
import AlgaFoodAPI.Domain.service.CadastroFormaDePagamento;
import AlgaFoodAPI.api.assembler.FormaDePagamentoInputDesassembler;
import AlgaFoodAPI.api.assembler.FormaDePagamentoModelAssembler;
import AlgaFoodAPI.api.model.FormaDePagamentoModel;
import AlgaFoodAPI.api.model.input.FormaDePagamentoInput;
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
    private FormaDePagamentoModelAssembler assembler;

    @Autowired
    private FormaDePagamentoInputDesassembler desassembler;

    @Autowired
    private FormaDePagamentoRepository repository;

    @Autowired
    private CadastroFormaDePagamento cadastroFormaDePagamento;

    @GetMapping
    public List<FormaDePagamentoModel> listar(){
        return assembler.toCollectionModel(repository.findAll());
    }

    @GetMapping("/{formaDePagamentoId}")
    @ResponseStatus(HttpStatus.OK)
    public FormaDePagamentoModel buscar(@PathVariable Long formaDePagamentoId){
        return assembler.toModel(cadastroFormaDePagamento.buscarOuFalhar(formaDePagamentoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaDePagamentoModel adicionar(@Valid @RequestBody FormaDePagamentoInput formaDePagamentoInput){
        FormaDePagamento formaDePagamento = desassembler.toDomainObject(formaDePagamentoInput);
        return assembler.toModel(cadastroFormaDePagamento.salvar(formaDePagamento));
    }

    @PutMapping("/{formaDePagamentoId}")
    @ResponseStatus(HttpStatus.OK)
    public FormaDePagamentoModel atualizar(@PathVariable Long formaDePagamentoId,@Valid @RequestBody FormaDePagamentoInput formaDePagamentoInput){
        FormaDePagamento formaDePagamentoAtual = cadastroFormaDePagamento.buscarOuFalhar(formaDePagamentoId);
        FormaDePagamento formaDePagamento = desassembler.toDomainObject(formaDePagamentoInput);
        BeanUtils.copyProperties(formaDePagamento,formaDePagamentoAtual,"id");
        return assembler.toModel(cadastroFormaDePagamento.salvar(formaDePagamentoAtual));
    }

    @DeleteMapping("/{formaDePagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long formaDePagamentoId){
        cadastroFormaDePagamento.excluir(formaDePagamentoId);
    }
}
