package AlgaFoodAPI.api.assembler;

import AlgaFoodAPI.Domain.Model.Cozinha;
import AlgaFoodAPI.api.model.input.CozinhaInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaInputDesassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cozinha toDomainObject(CozinhaInput cozinhaInput){
        return modelMapper.map(cozinhaInput,Cozinha.class);
    }
}
