package AlgaFoodAPI.api.assembler;

import AlgaFoodAPI.Domain.Model.Cidade;
import AlgaFoodAPI.Domain.Model.FormaDePagamento;
import AlgaFoodAPI.api.model.CidadeModel;
import AlgaFoodAPI.api.model.FormaDePagamentoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaDePagamentoModelAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public FormaDePagamentoModel toModel(FormaDePagamento formaDePagamento){
        return modelMapper.map(formaDePagamento,FormaDePagamentoModel.class);
    }

    public List<FormaDePagamentoModel> toCollectionModel(List<FormaDePagamento>formasDePagamento ){
        return  formasDePagamento.stream()
                .map(formaDePagamento -> toModel(formaDePagamento))
                .collect(Collectors.toList());
    }
}
