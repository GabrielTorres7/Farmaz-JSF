/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.service;

import br.cefetmg.farmaz.model.dominio.Endereco;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface ManterEndereco extends Remote{
    public Long inserirEndereco(Endereco endereco) throws PersistenciaException, LogicaNegocioException, RemoteException;
    public boolean atualizarEndereco(Endereco endereco) throws PersistenciaException, LogicaNegocioException, RemoteException;
    public boolean deletarEndereco(Long enderecoId) throws PersistenciaException, RemoteException;
    public Endereco getEnderecoById(Long enderecoId) throws PersistenciaException, RemoteException;
    public List<Endereco> getEnderecosByClienteId(Long clienteId) throws PersistenciaException, RemoteException;
}
