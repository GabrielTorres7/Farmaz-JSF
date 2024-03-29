/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.dominio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Gabriel
 */
@Entity(name="produto")
public class Produto implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq_produto")
    Long id;
   
   
    String nome;  
    
    @Column(name = "Receita")
    boolean isReceita;
    
    
    String descricao;
    
   
    String laboratorio;
    
    @Column(name = "Cadastro-anvisa")
    String cadastroAnvisa;

    public Produto() {
    }

    public Produto(Long id, String nome, boolean isReceita, String descricao, String laboratorio, String cadastroAnvisa) {
        this.id = id;
        this.nome = nome;
        this.isReceita = isReceita;
        this.descricao = descricao;
        this.laboratorio = laboratorio;
        this.cadastroAnvisa = cadastroAnvisa;
    }

    public Produto(Long id, String nome, boolean isReceita, String descricao) {
        this.id = id;
        this.nome = nome;
        this.isReceita = isReceita;
        this.descricao = descricao;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long produtoId) {
        this.id = produtoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isReceita() {
        return isReceita;
    }

    public void setReceita(boolean receita) {
        this.isReceita = receita;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getCadastroAnvisa() {
        return cadastroAnvisa;
    }

    public void setCadastroAnvisa(String cadastroAnvisa) {
        this.cadastroAnvisa = cadastroAnvisa;
    }
    
}
