package validadores;

import models.PedidoVenda;

import java.util.ArrayList;
import java.util.List;

public class ValidadorPedidoVenda extends Validador{

    private PedidoVenda pedidoVenda;

    public List<IValidadorPedidoVendas> validadoresPedidoVenda = new ArrayList<>();

    public ValidadorPedidoVenda(PedidoVenda pedidoVenda){
        this.pedidoVenda = pedidoVenda;

        this.validadoresPedidoVenda.add(new ValidadorDataValidade());
        this.validadoresPedidoVenda.add(new ValidadorQuantidade());

    }

    @Override
    public boolean ehValido() {
        for(IValidadorPedidoVendas validadorPedidoVendas : validadoresPedidoVenda){
            String erro = validadorPedidoVendas.validar(pedidoVenda);

            if(erro != null){
                addErro(erro);
            }
        }
        return !(getErros().size() > 0);
    }
}
