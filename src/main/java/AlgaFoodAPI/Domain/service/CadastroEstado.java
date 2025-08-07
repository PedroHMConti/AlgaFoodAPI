package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.EntidadeEmUsoException;
import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Model.Estado;
import AlgaFoodAPI.Domain.Repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstado {

    @Autowired
    private EstadoRepository estadoRepository;

    public void excluir(Long estadoId) {
        try {
            Estado estado = estadoRepository.findById(estadoId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("não exite cadastro para o estado com o código %d",estadoId)));
            estadoRepository.delete(estado);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("O estado com o código %d não pode ser removido, pois está em uso", estadoId));
        }
    }

    public Estado salvar(Estado estado){
        return estadoRepository.save(estado);
    }
}
