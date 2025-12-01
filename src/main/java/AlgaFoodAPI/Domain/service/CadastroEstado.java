package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.EntidadeEmUsoException;
import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
//import AlgaFoodAPI.Domain.Exception.EstadoNaoEncontradoException;
import AlgaFoodAPI.Domain.Exception.EstadoNaoEncontradoException;
import AlgaFoodAPI.Domain.Model.Estado;
import AlgaFoodAPI.Domain.Repository.EstadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstado {

    public static final String MSG_ESTADO_NOT_FOUND = "não exite cadastro para o estado com o código %d";
    public static final String MSG_ESTADO_EM_USO = "O estado com o código %d não pode ser removido, pois está em uso";
    @Autowired
    private EstadoRepository estadoRepository;

    @Transactional
    public void excluir(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);
            estadoRepository.flush();
            buscarOuFalhar(estadoId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, estadoId));
        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException(estadoId);
        }
    }

    @Transactional
    public Estado salvar(Estado estado){
        return estadoRepository.save(estado);
    }

    public Estado buscarOuFalhar(Long estadoId){
        return estadoRepository.findById(estadoId).orElseThrow(() -> new EstadoNaoEncontradoException(String.format(MSG_ESTADO_NOT_FOUND,estadoId)));

    }
}
