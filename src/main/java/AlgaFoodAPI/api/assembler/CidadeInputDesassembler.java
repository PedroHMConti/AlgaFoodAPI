package AlgaFoodAPI.api.assembler;

import AlgaFoodAPI.Domain.Model.Cidade;
import AlgaFoodAPI.Domain.Model.Estado;
import AlgaFoodAPI.api.model.input.CidadeInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeInputDesassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cidade toDomainObject(CidadeInput cidadeInput){
        return modelMapper.map(cidadeInput,Cidade.class);
    }

    public void copyToDomainObject(CidadeInput cidadeInput,Cidade cidade){
        cidade.setEstado(new Estado());
        modelMapper.map(cidadeInput,cidade);
    }
}
