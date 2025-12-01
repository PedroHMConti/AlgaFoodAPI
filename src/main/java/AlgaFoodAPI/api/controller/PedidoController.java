package AlgaFoodAPI.api.controller;

import AlgaFoodAPI.Domain.Model.Pedido;
import AlgaFoodAPI.Domain.Repository.PedidoRepository;
import AlgaFoodAPI.Domain.Repository.ProdutoRepository;
import AlgaFoodAPI.Domain.service.CadastroPedido;
import AlgaFoodAPI.Domain.service.EmissaoPedidoService;
import AlgaFoodAPI.api.assembler.PedidoModelAssembler;
import AlgaFoodAPI.api.assembler.PedidoResumoModelAssembler;
import AlgaFoodAPI.api.model.PedidoModel;
import AlgaFoodAPI.api.model.PedidoResumoModel;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmissaoPedidoService emissaoPedido;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @GetMapping
    public List<PedidoResumoModel> listar() {
        List<Pedido> todosPedidos = pedidoRepository.findAll();

        return pedidoResumoModelAssembler.toCollectionModel(todosPedidos);
    }

    @GetMapping("/{pedidoId}")
    public PedidoModel buscar(@PathVariable Long pedidoId) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);

        return pedidoModelAssembler.toModel(pedido);
    }

}