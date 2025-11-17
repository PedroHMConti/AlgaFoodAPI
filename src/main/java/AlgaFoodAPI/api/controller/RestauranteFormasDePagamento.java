package AlgaFoodAPI.api.controller;

import AlgaFoodAPI.Domain.Model.FormaDePagamento;
import AlgaFoodAPI.Domain.Model.Restaurante;
import AlgaFoodAPI.Domain.service.CadastroRestaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-de-pagamento")
public class RestauranteFormasDePagamento {

    @Autowired
    private CadastroRestaurante cadastroRestaurante;

    @GetMapping
    public List<FormaDePagamento> listar(@PathVariable Long restauranteId){
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        return restaurante.getFormasDePagamento();
    }

    @DeleteMapping("{formaDePagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long formaDePagamentoId,@PathVariable Long restauranteId){
        cadastroRestaurante.desassociarFormaDePagamento(restauranteId,formaDePagamentoId);
    }

    @PutMapping("{formaDePagamentoId}")
    public void associar(@PathVariable Long formaDePagamentoId,@PathVariable Long restauranteId){
        cadastroRestaurante.associarFormaDePagamento(restauranteId,formaDePagamentoId);
    }
}
