/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.bean;

import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.proxy.ManterDisponibilidadeProxy;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Gabriel
 */
@ManagedBean(name="ListarFarmaciaMB")
@ViewScoped
public class ListarFarmaciaMB {
    
    ManterDisponibilidadeProxy manterDisponibilidade;
    private Disponibilidade disponibilidadeSelecionada;
    
    public ListarFarmaciaMB() throws SocketException, UnknownHostException {
        this.manterDisponibilidade = new ManterDisponibilidadeProxy();
    }
}
