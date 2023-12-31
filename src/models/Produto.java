package models;
import java.util.Date;

public abstract class Produto {
    private int id;
    private String descricao;
    private double preco;
    private Date dataValidade;

    public Produto(String descricao, double preco, Date dataValidade) {
        this.descricao = descricao;
        this.preco = preco;
        this.dataValidade = dataValidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }
}
