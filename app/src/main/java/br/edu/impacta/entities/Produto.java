package br.edu.impacta.entities;

/**
 * Created by Administrador on 02/10/2017.
 */

public class Produto {

    private String nome;
    private int id;
    private Double valor;
    private String categoria;

    public Produto(){

    }

    public Produto(String nome, String categoria, Double valor) {
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
