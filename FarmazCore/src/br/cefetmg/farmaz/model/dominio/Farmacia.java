/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.dominio;

/**
 *
 * @author Gabriel
 */
public class Farmacia {
    Long cadastroPrefeitura;
    Long codCidade;
    Long codUf;
    String cnpj;
    String nome;
    int cep;
    String bairro;
    String rua;
    int numero;
    String email;
    String senha;

    public Farmacia() {
    }

    public Farmacia(Long cadastroPrefeitura, Long codCidade, Long codUf, String cnpj, String nome, int cep, String bairro, String rua, int numero, String email, String senha) {
        this.cadastroPrefeitura = cadastroPrefeitura;
        this.codCidade = codCidade;
        this.codUf = codUf;
        this.cnpj = cnpj;
        this.nome = nome;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.email = email;
        this.senha = senha;
    }

    public Long getCadastroPrefeitura() {
        return cadastroPrefeitura;
    }

    public void setCadastroPrefeitura(Long cadastroPrefeitura) {
        this.cadastroPrefeitura = cadastroPrefeitura;
    }

    public Long getCodCidade() {
        return codCidade;
    }

    public void setCodCidade(Long codCidade) {
        this.codCidade = codCidade;
    }

    public Long getCodUf() {
        return codUf;
    }

    public void setCodUf(Long codUf) {
        this.codUf = codUf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
