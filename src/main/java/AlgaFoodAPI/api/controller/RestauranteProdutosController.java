package AlgaFoodAPI.api.controller;


import AlgaFoodAPI.Domain.Model.Produto;
import AlgaFoodAPI.Domain.Model.Restaurante;
import AlgaFoodAPI.Domain.Repository.RestauranteRepository;
import AlgaFoodAPI.Domain.service.CadastroProduto;
import AlgaFoodAPI.Domain.service.CadastroRestaurante;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutosController {
    @Autowired
    private CadastroRestaurante cadastroRestaurante;

    @Autowired
    private CadastroProduto cadastroProduto;

    @GetMapping("/{produtoId}")
    public Produto buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        return  cadastroProduto.buscarOuFalhar( produtoId);
    }


    @GetMapping
    public List<Produto> listar(@PathVariable Long restauranteId){
        List<Produto> produtos = cadastroRestaurante.buscarOuFalhar(restauranteId).getProdutos();
        return produtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionarProduto(@PathVariable Long restauranteId,@RequestBody @Valid Produto produto){
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        produto.setRestaurante(restaurante);
        Produto produtoNovo = cadastroProduto.salvar(produto);
    }

    @DeleteMapping("{produtoId}")
    public void excluirProduto(@PathVariable Long restauranteId,@PathVariable Long produtoId){
        cadastroProduto.excluir(produtoId);
    }

}
