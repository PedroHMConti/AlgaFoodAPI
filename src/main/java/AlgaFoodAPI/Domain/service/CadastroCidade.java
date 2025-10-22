package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.CidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Exception.EntidadeEmUsoException;
import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Model.Cidade;
import AlgaFoodAPI.Domain.Model.Estado;
import AlgaFoodAPI.Domain.Repository.CidadeRepository;
import AlgaFoodAPI.Domain.Repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidade {

    public static final String MSG_CIDADE_NOT_FOUND = "não existe cadastro para a cidade com o código %d";
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstado cadastroEstado;

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();

        Estado estado = cadastroEstado.buscarOuFalhar(estadoId);

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    public void excluir(Long cidadeId) {
        Cidade cidade = this.buscarOuFalhar(cidadeId);
        try {
            cidadeRepository.deleteById(cidadeId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cidade de código %d não pode ser removida, pois está em uso", cidadeId));
        }catch (EmptyResultDataAccessException e){
            throw new CidadeNaoEncontradaException(cidadeId);
        }
    }

    public Cidade buscarOuFalhar(Long cidadeId){
        return cidadeRepository.findById(cidadeId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NOT_FOUND,cidadeId)));
    }
}
