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
    Long cnpj;
    String nome;
    Long cep;
    String bairro;
    String rua;
    Long numero;

    public Farmacia() {
    }

    public Farmacia(Long cadastroPrefeitura, Long codCidade, Long codUf, Long cnpj, String nome, Long cep, String bairro, String rua, Long numero) {
        this.cadastroPrefeitura = cadastroPrefeitura;
        this.codCidade = codCidade;
        this.codUf = codUf;
        this.cnpj = cnpj;
        this.nome = nome;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
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

    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCep() {
        return cep;
    }

    public void setCep(Long cep) {
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

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }
    
}
