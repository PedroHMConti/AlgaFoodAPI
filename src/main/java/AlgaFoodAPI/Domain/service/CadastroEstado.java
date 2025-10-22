package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.EntidadeEmUsoException;
import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Exception.EstadoNaoEncontradoException;
import AlgaFoodAPI.Domain.Model.Estado;
import AlgaFoodAPI.Domain.Repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstado {

    public static final String MSG_ESTADO_NOT_FOUND = "não exite cadastro para o estado com o código %d";
    @Autowired
    private EstadoRepository estadoRepository;

    public void excluir(Long estadoId) {
        Estado estado = this.buscarOuFalhar(estadoId);
        try {
            estadoRepository.deleteById(estadoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("O estado com o código %d não pode ser removido, pois está em uso", estadoId));
        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException(estadoId);
        }
    }

    public Estado salvar(Estado estado){
        return estadoRepository.save(estado);
    }

    public Estado buscarOuFalhar(Long estadoId){
        return estadoRepository.findById(estadoId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_ESTADO_NOT_FOUND,estadoId)));

    }
}
