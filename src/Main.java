import db.EstoqueDB;
import db.PedidoVendaDB;
import db.ProdutosDB;
import db.UsuariosDB;
import models.*;
import validadores.ValidadorPedidoVenda;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Main {
    static ProdutosDB produtosDB = new ProdutosDB();
    static UsuariosDB usuariosDB = new UsuariosDB();

    static EstoqueDB estoqueDB = new EstoqueDB();
    static PedidoVendaDB pedidoVendaDB = new PedidoVendaDB();

    public static void main(String[] args) throws Exception {
        System.out.println("----- SISTEMA DE PEDIDO DE VENDAS -----");

        int option;
        do {
            System.out.println("---------------------------------------------------");
            System.out.println("1 - Cadastrar produto                              ");
            System.out.println("2 - Listar produtos Cadastrados                    ");
            System.out.println("3 - Cadastrar usuario Adm                          ");
            System.out.println("4 - Cadastrar usuario Cliente                      ");
            System.out.println("5 - Listar todos os usuario                        ");
            System.out.println("6 - Cadastrar novo estoque de produto              ");
            System.out.println("7 - Listar todos estoque                           ");
            System.out.println("8 - Pedido de vendas                               ");
            System.out.println("9 - Listar Pedido de vendas                        ");
            System.out.println("0 - Sair do sistema                                ");
            System.out.println("---------------------------------------------------");
            Scanner scanner = new Scanner(System.in);

            System.out.print("Qual opção deseja realizar: ");
            option = scanner.nextInt();

            process(option);
        }while (option != 0);
    }
    public static void process(int option) throws Exception  {
        Scanner scanner = new Scanner(System.in);
        switch (option) {
            case 1: {
                System.out.print("Informe a descrição do produto: ");
                String descricao = scanner.next();

                System.out.print("Informe o preço R$ ");
                double preco = scanner.nextDouble();

                System.out.print("Informe a data de validade: ");
                String dataString = scanner.next();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date dataValidade = new SimpleDateFormat("dd/MM/yyyy").parse(dataString);

                CategoriaProduto novoProduto = new CategoriaProduto(descricao,preco,dataValidade);
                produtosDB.addNovoProduto(novoProduto);

                break;
            }
            case 2:{
                List<Produto> ListaDeProdutos = produtosDB.getProdutosList();

                for(Produto produto: ListaDeProdutos){
                    System.out.println("---------------------------------------------------");
                    System.out.println("-------- Lista de Produtos Cadastrados ! ----------");
                    System.out.println("---------------------------------------------------");
                    System.out.println("ID:  " + produto.getId());
                    System.out.println("Descicao: " + produto.getDescricao());
                    System.out.println("Preço R$ " + produto.getPreco());
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    System.out.println("Data de Validade: " + sdf.format(produto.getDataValidade()));
                    System.out.println("---------------------------------------------------");
                }
                break;
            }
            case 3:{

                System.out.print("Digite o nome do usuario admin: ");
                String nome = scanner.nextLine();

                Admin novoAdmin = new Admin(nome);
                usuariosDB.addNovoUsuario(novoAdmin);
                break;
            }
            case 4:{

                System.out.print("Digite o usuario cliente:");
                String nome = scanner.nextLine();

                Cliente novoCliente = new Cliente(nome);
                usuariosDB.addNovoUsuario(novoCliente);
                break;
            }
            case 5:{
                    System.out.println("-----------------------------------------");
                    System.out.println("----- Lista de usuarios cadastrados -----");
                    System.out.println("-----------------------------------------");
               for(Usuario usuario : usuariosDB.getUsuarioList()){
                   System.out.println("ID: " + usuario.getId());
                   System.out.println("Nome: " + usuario.getNome());
                   System.out.println("Tipo: " + usuario.getTipoUsuario());
                   System.out.println("-----------------------------------------");
               }
               break;
            }
            case 6:{

                System.out.println("-----------------------------------------");
                System.out.println("-----Cadastrando Estoque de produto -----");
                System.out.println("-----------------------------------------");

                System.out.print("Qual o ID do estoque: ");
                String id = scanner.nextLine();

                 System.out.print("Qual produto sera add ao estoque, informe o ID:");
                 int produtoId = scanner.nextInt();

                Produto produto = produtosDB.getProdutoPorId(produtoId);
                System.out.println("Produtod ID: " + produto.getId());
                System.out.println("Produto Descrição: " + produto.getDescricao());
                System.out.println("Validade: " + produto.getDataValidade());
                System.out.println("Preço: " + produto.getPreco());

                System.out.print("Qual a quantidade a ser adicionada no estoque:");
                int quantidade = scanner.nextInt();

                Estoque novoEstoque = new Estoque(id, produto, quantidade);
                estoqueDB.addNovoEstoque(novoEstoque);
                break;
            }
            case 7:{
                System.out.println("-----------------------------------------");
                System.out.println("-----Lista de Produtos em Estoque  -----");
                System.out.println("-----------------------------------------");
                for(Estoque estoque : estoqueDB.getEstoque()){
                    System.out.println("ID: " + estoque.getId());
                    System.out.println("Produto: " + estoque.getProduto().getDescricao());
                    System.out.println("Preço: " + estoque.getProduto().getPreco());
                    System.out.println("Quantidade: " + estoque.getQuantidade());
                    System.out.println("-----------------------------------------");
                }
                break;
            } case 8:{
                System.out.println("-----------------------------------------");
                System.out.println("--------     Pedido de vendas    --------");
                System.out.println("-----------------------------------------");

                System.out.println("Informe o ID d Cliente: ");
                int idCliente = scanner.nextInt();

                Cliente cliente = (Cliente) usuariosDB.getUsuarioPorId(idCliente);
                System.out.println("ID: " + cliente.getId());
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("Tipo: " + cliente.getTipoUsuario());
                System.out.println("-----------------------------------------");

                System.out.println("Informe o ID do produto: ");
                String idEstoque = scanner.next();

                Estoque estoque = estoqueDB.getEstoqueById(idEstoque);
                System.out.print("Estoque ID: " + estoque.getId());
                System.out.print("Estoque Descrição: " + estoque.getProduto().getDescricao());
                System.out.println("Validade: " + estoque.getProduto().getDataValidade());
                System.out.println("-----------------------------------------");

                System.out.print("Informe a quantidade a ser vendida: ");
                int quantidade = scanner.nextInt();

                PedidoVenda novoPedido = new PedidoVenda(cliente,estoque,quantidade);

                ValidadorPedidoVenda validadorPedidoVenda = new ValidadorPedidoVenda(novoPedido);
                if(validadorPedidoVenda.ehValido()){
                    pedidoVendaDB.addNovoPedodoVenda(novoPedido);
                }else {
                    System.out.println(validadorPedidoVenda.getErros());
                }

                break;
            } case 9:{
                System.out.println("-----------------------------------------");
                System.out.println("----- Lista de Pedidos de Venda -----");
                System.out.println("-----------------------------------------");

                for(PedidoVenda pedidoVenda : pedidoVendaDB.getPedidoVendas()){
                    System.out.println("ID: " + pedidoVenda.getId());
                    System.out.println("Cliente: " + pedidoVenda.getCliente().getNome());
                    System.out.println("Produto: " + pedidoVenda.getEstoque().getProduto().getDescricao());
                    System.out.println("Quantidade: " + pedidoVenda.getQuantidade());
                    System.out.println("-----------------------------------------");
                }
                break;
            }
        }
    }
}
