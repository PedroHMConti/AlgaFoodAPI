package AlgaFoodAPI.api.controller;

import AlgaFoodAPI.Domain.service.FluxoPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos/{codigo}")
public class FluxoPedidoController {

    @Autowired
    private FluxoPedido fluxoPedido;

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar(@PathVariable String codigo){
        fluxoPedido.confirmar(codigo);
    }

    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void cancelar(@PathVariable String codigo){
        fluxoPedido.cancelar(codigo);
    }

    @PutMapping("/entrega")
    @ResponseStatus(HttpStatus.OK)
    public void entregar(@PathVariable String codigo){
        fluxoPedido.entregar(codigo);
    }
}
