package AlgaFoodAPI.api.controller;


import AlgaFoodAPI.Domain.Exception.CozinhaNaoEncontradaException;
import AlgaFoodAPI.Domain.Exception.NegocioException;
import AlgaFoodAPI.Domain.Model.Cozinha;
import AlgaFoodAPI.Domain.Model.Restaurante;
import AlgaFoodAPI.Domain.Repository.RestauranteRepository;
import AlgaFoodAPI.Domain.service.CadastroRestaurante;
import AlgaFoodAPI.api.assembler.RestauranteInputDesassembler;
import AlgaFoodAPI.api.assembler.RestauranteModelAssembler;
import AlgaFoodAPI.api.model.CozinhaModel;
import AlgaFoodAPI.api.model.RestauranteModel;
import AlgaFoodAPI.api.model.input.RestauranteInput;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private CadastroRestaurante cadastroRestaurante;

    @Autowired
    private RestauranteRepository repo;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputDesassembler restauranteInputDesassembler;

    @GetMapping
    public List<RestauranteModel> listar() {
        return restauranteModelAssembler.toCollectionModel(repo.findAll());
    }


    @GetMapping("/{restauranteId}")
    public RestauranteModel buscar(@PathVariable Long restauranteId){
        Restaurante restaurante  = cadastroRestaurante.buscarOuFalhar(restauranteId);
        return restauranteModelAssembler.toModel(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar( @RequestBody @Valid RestauranteInput restauranteInput) {
        Restaurante restaurante = restauranteInputDesassembler.toDomainObject(restauranteInput);
        try {
            return restauranteModelAssembler.toModel( cadastroRestaurante.salvar(restaurante));

        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }


    @PutMapping("ativacoes")
    public void ativacaoMultipla(@RequestBody List<Long> restaurantesIds){
        cadastroRestaurante.ativar(restaurantesIds);
    }

    @DeleteMapping("ativacoes")
    public void destivacaoMultipla(@RequestBody List<Long> restaurantesIds){
        cadastroRestaurante.inativar(restaurantesIds);
    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel atualizar(@PathVariable Long restauranteId,@Valid @RequestBody RestauranteInput restauranteInput ) {
        Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
        restauranteInputDesassembler.copyToDomainObject(restauranteInput,restauranteAtual);
        return restauranteModelAssembler.toModel( cadastroRestaurante.salvar(restauranteAtual));
        }

    @DeleteMapping("/{restauranteId}")
    public void delete(@PathVariable Long restauranteId){
       cadastroRestaurante.excluir(restauranteId);
    }

    @GetMapping("/count-by-cozinha-id/{cozinhaId}")
    public int contar(@PathVariable  Long cozinhaId){
        return repo.countByCozinhaId(cozinhaId);
    }

    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId){
        cadastroRestaurante.ativar(restauranteId);
    }


    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long restauranteId){
        cadastroRestaurante.inativar(restauranteId);
    }

}



