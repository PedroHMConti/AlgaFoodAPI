package AlgaFoodAPI.api.controller;


import AlgaFoodAPI.Domain.Exception.EstadoNaoEncontradoException;
import AlgaFoodAPI.Domain.Exception.NegocioException;
import AlgaFoodAPI.Domain.Model.Cidade;
import AlgaFoodAPI.Domain.Repository.CidadeRepository;
import AlgaFoodAPI.Domain.Repository.EstadoRepository;
import AlgaFoodAPI.Domain.service.CadastroCidade;
import AlgaFoodAPI.api.assembler.CidadeInputDesassembler;
import AlgaFoodAPI.api.assembler.CidadeModelAssembler;
import AlgaFoodAPI.api.assembler.CozinhaInputDesassembler;
import AlgaFoodAPI.api.assembler.CozinhaModelAssembler;
import AlgaFoodAPI.api.model.CidadeModel;
import AlgaFoodAPI.api.model.input.CidadeInput;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CadastroCidade cadastroCidade;

    @Autowired
    private CidadeRepository repository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeInputDesassembler cidadeInputDesassembler;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @GetMapping
    public List<CidadeModel> listar() {
        return cidadeModelAssembler.toCollectionModel(repository.findAll());
    }

    @GetMapping("/{cidadeId}")
    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
    public CidadeModel buscar(@PathVariable Long cidadeId) {
        return cidadeModelAssembler.toModel(cadastroCidade.buscarOuFalhar(cidadeId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar(@Valid @RequestBody CidadeInput cidadeInput) {
        Cidade cidade = cidadeInputDesassembler.toDomainObject(cidadeInput);
        return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidade));
    }

    @PutMapping("/{cidadeId}")
    public CidadeModel atualizar(@PathVariable Long cidadeId, @Valid @RequestBody CidadeInput cidadeInput) {
        Cidade cidade = cidadeInputDesassembler.toDomainObject(cidadeInput);
        Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidadeAtual));
    }
    @DeleteMapping("/{cidadeId}")
    public void delete(@PathVariable Long cidadeId) {
        cadastroCidade.excluir(cidadeId);
    }
}