package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Model.Produto;
import AlgaFoodAPI.Domain.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

public class CadastroProduto {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto  salvar(Produto produto){
        return produtoRepository.save(produto);
    }

    public void excluir(Long produtoId){
        try{
            produtoRepository.deleteById(produtoId);
        }catch(EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("não existe produto cadastrado com o código %d%n",produtoId));
        }
    }
}
