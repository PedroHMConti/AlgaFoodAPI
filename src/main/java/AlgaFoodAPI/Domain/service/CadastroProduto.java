package AlgaFoodAPI.Domain.service;

import AlgaFoodAPI.Domain.Exception.EntidadeNaoEncontradaException;
import AlgaFoodAPI.Domain.Exception.NegocioException;
import AlgaFoodAPI.Domain.Exception.ProdutoNaoEncontradoException;
import AlgaFoodAPI.Domain.Model.Produto;
import AlgaFoodAPI.Domain.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroProduto {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto  salvar(Produto produto){
        return produtoRepository.save(produto);
    }

    public void excluir(Long produtoId){
        try{
            produtoRepository.deleteById(produtoId);
            produtoRepository.flush();
        }catch(EmptyResultDataAccessException e){
            throw new NegocioException(String.format("não existe produto cadastrado com o código %d%n",produtoId));
        }
    }

    public Produto buscarOuFalhar(Long produtoId){
        return produtoRepository.findById(produtoId).orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
    }
}
