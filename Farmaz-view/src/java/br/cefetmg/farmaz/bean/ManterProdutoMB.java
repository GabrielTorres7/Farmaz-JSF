/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.bean;

import br.cefetmg.farmaz.model.dominio.Produto;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.proxy.ManterProdutoProxy;
import java.io.Serializable;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Gabriel
 */
@ManagedBean(name="ManterProdutoMB")
@ViewScoped
public class ManterProdutoMB implements Serializable{
    
    ManterProdutoProxy manterProduto;
     
    public ManterProdutoMB() throws SocketException, UnknownHostException {
        this.manterProduto = new ManterProdutoProxy();
    }

    public List<Produto> getListProduto() throws PersistenciaException {
        return manterProduto.listAll();
    }

}
