package AlgaFoodAPI.api.controller;

import AlgaFoodAPI.Domain.Model.Pedido;
import AlgaFoodAPI.Domain.Repository.PedidoRepository;
import AlgaFoodAPI.Domain.Repository.ProdutoRepository;
import AlgaFoodAPI.Domain.service.CadastroPedido;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    CadastroPedido cadastroPedido;

    @Autowired
    PedidoRepository pedidoRepository;

    @GetMapping
    public List<Pedido> pedidos(){
        return pedidoRepository.findAll();
    }

    @GetMapping("{pedidoId}")
    public Pedido buscar(@PathVariable Long pedidoId){
        return cadastroPedido.buscarOuFalhar(pedidoId);
    }

    @PostMapping
    public Pedido adicionar(@RequestBody @Valid Pedido pedido){
        return cadastroPedido.salvar(pedido);
    }

    @PutMapping("{pedidoId}")
    public Pedido atualizar(@PathVariable Long pedidoId, @RequestBody @Valid Pedido pedido){
        Pedido pedidoAtual = cadastroPedido.buscarOuFalhar(pedidoId);
        BeanUtils.copyProperties(pedido,pedidoAtual,"id");
        return cadastroPedido.salvar(pedidoAtual);
    }

    @DeleteMapping("{pedidoId}")
    public void excluir(@PathVariable Long pedidoId){
        cadastroPedido.excluir(pedidoId);
    }


}
