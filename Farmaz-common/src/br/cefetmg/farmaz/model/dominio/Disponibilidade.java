/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.dominio;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

   
/**
 *
 * @author Hiago
 */
@Entity(name="disponibilidade")
public class Disponibilidade implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq_disponibilidade")
    private Long id;
    
    @Column(name = "seq_produto")
    private Long produtoSeq;
    
    @Column(name = "cadastro_prefeitura")
    private String farmaciaCadastro;
    
    @Column(name = "estoque")
    private int estoque;
    
    @Column(name = "preco")
    private double preco;
    
    @Column(name = "avaliacao")
    private int avaliacao;

public Disponibilidade() {
}

    public Disponibilidade(Long id, Long produtoSeq, String farmaciaCadastro, int estoque, double preco, int avaliacao) {
        this.id = id;
        this.produtoSeq = produtoSeq;
        this.farmaciaCadastro = farmaciaCadastro;
        this.estoque = estoque;
        this.preco = preco;
        this.avaliacao = avaliacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProdutoSeq() {
        return produtoSeq;
    }

    public void setProdutoSeq(Long produtoSeq) {
        this.produtoSeq = produtoSeq;
    }

    public String getFarmaciaCadastro() {
        return farmaciaCadastro;
    }

    public void setFarmaciaCadastro(String farmaciaCadastro) {
        this.farmaciaCadastro = farmaciaCadastro;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }


}