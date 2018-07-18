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
public class Endereco {
    Long enderecoId;
    Long clienteId;
    Long codCidade;
    Long codUf;
    Long cep;
    String bairro;
    String rua;
    Long numero;
    String complemento;

    public Endereco() {
    }

    public Endereco(Long enderecoId, Long clienteId, Long codCidade, Long codUf, Long cep, String bairro, String rua, Long numero, String complemento) {
        this.enderecoId = enderecoId;
        this.clienteId = clienteId;
        this.codCidade = codCidade;
        this.codUf = codUf;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
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

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
}
