package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Model.Endereco;
import AlgaFoodAPI.Domain.Repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

public class CadastroEndereco {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco salvar(Endereco endereco){
        try{
            return enderecoRepository.save(endereco);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void excluir(Long enderecoId){
        try{
            enderecoRepository.deleteById(enderecoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("não existe cadastro para o endereço com o código %d%n",enderecoId));
        }
    }
}
