package validadores;

import models.PedidoVenda;

public class ValidadorQuantidade implements IValidadorPedidoVendas {
    @Override
    public String validar(PedidoVenda pedidoVenda) {
        if(pedidoVenda.getQuantidade() > pedidoVenda.getEstoque().getQuantidade()){
            return "Quantidade indisponivel no estoque";
        }
        return null;
    }
}
