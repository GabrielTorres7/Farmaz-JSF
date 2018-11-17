/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.service;

import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface ManterDisponibilidade extends Remote{
    public Long inserirDisponibilidade(Disponibilidade disponibilidade) throws PersistenciaException, LogicaNegocioException, RemoteException;
    public boolean atualizarDisponibilidade(Disponibilidade disponibilidade) throws PersistenciaException, LogicaNegocioException, RemoteException;
    public boolean deletarDisponibilidade(Long disponibilidadeId) throws PersistenciaException, RemoteException;
    public Disponibilidade getDisponibilidadeById(Long disponibilidadeId) throws PersistenciaException, RemoteException;
    public List<Disponibilidade> getDisponibilidadeByProdutoId(Long produtoId) throws PersistenciaException, RemoteException;
    public List<Disponibilidade> getDisponibilidadeByFarmaciaId(String farmaciaId) throws PersistenciaException, RemoteException;
}
