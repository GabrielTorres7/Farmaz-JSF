/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.server;

import br.cefetmg.farmaz.model.daoImpl.CidadeDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.ClienteDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.DisponibilidadeDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.EnderecoDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.EstadoDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.FarmaciaDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.ItemPedidoDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.PedidoDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.ProdutoDAOImpl;
import br.cefetmg.farmaz.model.service.ManterCidade;
import br.cefetmg.farmaz.model.service.ManterCliente;
import br.cefetmg.farmaz.model.service.ManterDisponibilidade;
import br.cefetmg.farmaz.model.service.ManterEndereco;
import br.cefetmg.farmaz.model.service.ManterEstado;
import br.cefetmg.farmaz.model.service.ManterFarmacia;
import br.cefetmg.farmaz.model.service.ManterItemPedido;
import br.cefetmg.farmaz.model.service.ManterPedido;
import br.cefetmg.farmaz.model.service.ManterProduto;
import br.cefetmg.farmaz.model.serviceImpl.ManterCidadeImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterClienteImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterDisponibilidadeImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterEnderecoImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterEstadoImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterFarmaciaImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterItemPedidoImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterPedidoImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterProdutoImpl;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Gabriel
 */
public class Server {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        Registry registry = LocateRegistry.createRegistry(2345);

        //Registra o serviço ManterCidade
        ManterCidade manterCidade = new ManterCidadeImpl(CidadeDAOImpl.getInstance());
        registry.rebind("ManterCidade", manterCidade); // registra este objeto

        //Registra o serviço ManterCliente
        ManterCliente manterCliente = new ManterClienteImpl(ClienteDAOImpl.getInstance());
        registry.rebind("ManterCliente", manterCliente); // registra este objeto

        //Registra o serviço ManterDisponibilidade
        ManterDisponibilidade manterDisponibilidade = new ManterDisponibilidadeImpl(DisponibilidadeDAOImpl.getInstance());
        registry.rebind("ManterDisponibilidade", manterDisponibilidade); // registra este objeto

        //Registra o serviço ManterEndereço
        ManterEndereco manterEndereco = new ManterEnderecoImpl(EnderecoDAOImpl.getInstance());
        registry.rebind("ManterEndereco", manterEndereco); // registra este objeto

        //Registra o serviço ManterEstado
        ManterEstado manterEstado = new ManterEstadoImpl(EstadoDAOImpl.getInstance());
        registry.rebind("ManterEstado", manterEstado); // registra este objeto

        //Registra o serviço ManterFarmacia
        ManterFarmacia manterFarmacia = new ManterFarmaciaImpl(FarmaciaDAOImpl.getInstance());
        registry.rebind("ManterFarmacia", manterFarmacia); // registra este objeto

        //Registra o serviço ManterItemPedido
        ManterItemPedido manterItemPedido = new ManterItemPedidoImpl(ItemPedidoDAOImpl.getInstance());
        registry.rebind("ManterItemPedido", manterItemPedido); // registra este objeto

        //Registra o serviço ManterPedido
        ManterPedido manterPedido = new ManterPedidoImpl(PedidoDAOImpl.getInstance());
        registry.rebind("ManterPedido", manterPedido); // registra este objeto

        //Registra o serviço ManterProduto
        ManterProduto manterProduto = new ManterProdutoImpl(ProdutoDAOImpl.getInstance());
        registry.rebind("ManterProduto", manterProduto); // registra este objeto
    }

}
