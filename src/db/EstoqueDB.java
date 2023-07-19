package db;

import models.Estoque;
import models.Produto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstoqueDB {
    private Map<String, Estoque> estoqueDBMap = new HashMap<>();


    public Map<String, Estoque> getEstoqueDBMap() {
        return estoqueDBMap;
    }

    public List<Estoque> getEstoque(){
        List<Estoque> estoques = new ArrayList<>();
        for (Map.Entry<String, Estoque> estoque : estoqueDBMap.entrySet()){
            estoques.add(estoque.getValue());
        }
        return estoques;
    }


    public Estoque getEstoqueById(String id){
       return estoqueDBMap.get(id);
    }

    public  void  addNovoEstoque(Estoque estoque){
        estoqueDBMap.put(estoque.getId(), estoque);
    }

}
