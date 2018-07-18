/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.dominio;

import java.util.Date;

/**
 *
 * @author Gabriel
 */
public class Pedido {
    Long pedidoId;
    Long clienteId;
    Long farmaciaId;
    Date dataHora;
    char idtStatus;
    Long pagamento;

    public Pedido() {
    }

    public Pedido(Long pedidoId, Long clienteId, Long farmaciaId, Date dataHora, char idtStatus, Long pagamento) {
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

    public Long getFarmaciaId() {
        return farmaciaId;
    }

    public void setFarmaciaId(Long farmaciaId) {
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

    public Long getPagamento() {
        return pagamento;
    }

    public void setPagamento(Long pagamento) {
        this.pagamento = pagamento;
    }
    
}
