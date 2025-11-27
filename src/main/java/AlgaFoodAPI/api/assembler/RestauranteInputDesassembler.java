package AlgaFoodAPI.api.assembler;

import AlgaFoodAPI.Domain.Model.Cozinha;
import AlgaFoodAPI.Domain.Model.Restaurante;
import AlgaFoodAPI.api.model.input.RestauranteInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDesassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainObject(RestauranteInput restauranteInput){
        return modelMapper.map(restauranteInput,Restaurante.class);
    }
    public void copyToDomainObject(RestauranteInput restauranteInput,Restaurante restaurante){
        restaurante.setCozinha(new Cozinha());

        modelMapper.map(restauranteInput,restaurante);
    }
}
