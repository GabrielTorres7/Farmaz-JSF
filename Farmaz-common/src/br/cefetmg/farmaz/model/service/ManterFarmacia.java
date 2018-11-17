/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.service;

import br.cefetmg.farmaz.model.dominio.Farmacia;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface ManterFarmacia extends Remote{
    public Long cadastrarFarmacia(Farmacia farmacia) throws PersistenciaException, LogicaNegocioException, RemoteException;
    public boolean atualizarFarmacia(Farmacia farmacia) throws PersistenciaException, LogicaNegocioException, RemoteException;
    public boolean deletarFarmacia(String farmaciaId) throws PersistenciaException, RemoteException;
    public Farmacia getFarmaciaById(String farmaciaId) throws PersistenciaException, RemoteException;
    public Farmacia getFarmaciaByEmail(String email) throws PersistenciaException, RemoteException;
    public Farmacia getFarmaciaByEmailSenha(String email, String senha) throws PersistenciaException, RemoteException;
    public List<Farmacia> listAll() throws PersistenciaException, RemoteException;
}
