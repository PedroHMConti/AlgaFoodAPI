package AlgaFoodAPI.api.assembler;

import AlgaFoodAPI.Domain.Model.Cidade;
import AlgaFoodAPI.Domain.Model.Estado;
import AlgaFoodAPI.Domain.Model.FormaDePagamento;
import AlgaFoodAPI.api.model.input.CidadeInput;
import AlgaFoodAPI.api.model.input.FormaDePagamentoInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaDePagamentoInputDesassembler {
    @Autowired
    private ModelMapper modelMapper;

    public FormaDePagamento toDomainObject(FormaDePagamentoInput formaDePagamentoInput){
        return modelMapper.map(formaDePagamentoInput,FormaDePagamento.class);
    }

}
