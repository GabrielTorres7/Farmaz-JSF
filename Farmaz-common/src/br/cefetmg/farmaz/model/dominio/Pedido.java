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
 * @author Gabriel
 */
@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq_pedido")
    private Long pedidoId;

    @Column(name = "seq_cliente")
    private Long clienteId;

    @Column(name = "cadastro_prefeitura")
    private String farmaciaId;

    @Temporal(TemporalType.DATE)
    @Column(name = "data")
    private Date dataHora;

    @Column(name = "status")
    private char idtStatus;

    @Column(name = "token_pagseguro")
    private double pagamento;
    private int troco;
    private double valor;

    public Pedido() {
    }

    public Pedido(Long pedidoId, Long clienteId, String farmaciaId, Date dataHora, char idtStatus, double pagamento) {
        this.pedidoId = pedidoId;
        this.clienteId = clienteId;
        this.farmaciaId = farmaciaId;
        this.dataHora = dataHora;
        this.idtStatus = idtStatus;
        this.pagamento = pagamento;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getFarmaciaId() {
        return farmaciaId;
    }

    public void setFarmaciaId(String farmaciaId) {
        this.farmaciaId = farmaciaId;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public char getIdtStatus() {
        return idtStatus;
    }

    public void setIdtStatus(char idtStatus) {
        this.idtStatus = idtStatus;
    }

    public double getPagamento() {
        return pagamento;
    }

    public void setPagamento(double pagamento) {
        this.pagamento = pagamento;
    }

    public int getTroco() {
        return troco;
    }

    public void setTroco(int troco) {
        this.troco = troco;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
