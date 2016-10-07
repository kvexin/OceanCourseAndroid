package com.gleidesilva.oceancourseandroid;

/**
 * Created by gleides on 06/10/16.
 */

public class Pedido {
    private int quantidade;
    private int preco;
    private boolean isCreme;
    private boolean isChocolate;
    private String nome;

    public Pedido(int quantidade, int preco, boolean hasCreme, boolean hasChocolate, String nome) {
        this.quantidade = quantidade;
        this.preco = preco;
        this.isCreme = hasCreme;
        this.isChocolate = hasChocolate;
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public boolean isCreme() {
        return isCreme;
    }

    public void setCreme(boolean creme) {
        this.isCreme = creme;
    }

    public boolean isChocolate() {
        return isChocolate;
    }

    public void setChocolate(boolean chocolate) {
        this.isChocolate = chocolate;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
