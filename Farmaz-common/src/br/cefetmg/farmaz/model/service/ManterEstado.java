/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.service;

import br.cefetmg.farmaz.model.dominio.Estado;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface ManterEstado extends Remote{
    public Long cadastrarEstado(Estado estado) throws PersistenciaException, LogicaNegocioException, RemoteException;
    public boolean atualizarEstado(Estado estado) throws PersistenciaException, LogicaNegocioException, RemoteException;
    public boolean deletarEstado(Long estadoId) throws PersistenciaException, RemoteException;
    public Estado getEstadoById(Long estadoId) throws PersistenciaException, RemoteException;
    public Estado getEstadoBySigla(String sigla) throws PersistenciaException, RemoteException;
    public List<Estado> getAll() throws PersistenciaException, RemoteException;   
}
