package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.EntidadeEmUsoException;
import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Model.Cidade;
import AlgaFoodAPI.Domain.Model.Estado;
import AlgaFoodAPI.Domain.Repository.CidadeRepository;
import AlgaFoodAPI.Domain.Repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidade {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();

        Estado estado = estadoRepository.buscar(estadoId);
        if (estado == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de estado com o código %d", estadoId));
        }

        cidade.setEstado(estado);
        return cidadeRepository.adicionar(cidade);
    }

    public void delete(Long cidadeId) {
        try {
            Cidade cidade = cidadeRepository.buscar(cidadeId);
            if (cidade == null) {
                throw new EntidadeNaoEncontradaException(
                        String.format("Não existe uma cidade com o código %d", cidadeId));
            }

            cidadeRepository.remover(cidade);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cidade de código %d não pode ser removida, pois está em uso", cidadeId));
        }
    }
}
