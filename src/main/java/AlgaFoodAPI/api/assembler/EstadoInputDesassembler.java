package AlgaFoodAPI.api.assembler;

import AlgaFoodAPI.Domain.Model.Estado;
import AlgaFoodAPI.api.model.input.EstadoInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoInputDesassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Estado toDomainObject(EstadoInput estadoInput){
        return modelMapper.map(estadoInput,Estado.class);
    }

}
