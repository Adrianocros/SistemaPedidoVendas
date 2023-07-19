package db;
import models.Produto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutosDB {
    private Map<Integer, Produto> produtosMap = new HashMap<>();

    public List<Produto> getProdutosList() {
        List<Produto> produtos = new ArrayList<>();
        for (Map.Entry<Integer, Produto> produto : produtosMap.entrySet()){
            produtos.add(produto.getValue());
        }
        return produtos;
    }

    //Incremente automaticamente o ID do Produto
    public void addNovoProduto(Produto produto){
        int id = produtosMap.size() + 1;
        produto.setId(id);
        produtosMap.put(produto.getId(),produto);
    }

    public Produto getProdutoPorId(int id){
       return produtosMap.get(id);
    }


}
