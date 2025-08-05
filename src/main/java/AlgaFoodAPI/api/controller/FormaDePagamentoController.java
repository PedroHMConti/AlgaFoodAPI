package AlgaFoodAPI.api.controller;


import AlgaFoodAPI.Domain.Model.FormaDePagamento;
import AlgaFoodAPI.Domain.Repository.FormaDePagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/FormasDePagamento")
@ResponseBody
public class FormaDePagamentoController {

    @Autowired
    private FormaDePagamentoRepository repository;

    @GetMapping
    public List<FormaDePagamento> listar(){
        return repository.listar();
    }
}
