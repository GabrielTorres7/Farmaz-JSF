/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.serviceImpl;

import br.cefetmg.farmaz.model.daoImpl.FarmaciaDAOImpl;
import br.cefetmg.farmaz.model.dominio.Farmacia;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterFarmacia;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class ManterFarmaciaImpl implements ManterFarmacia{
    
    private final FarmaciaDAOImpl farmaciaDAO;

    public ManterFarmaciaImpl(FarmaciaDAOImpl farmaciaDAO) {
        this.farmaciaDAO = farmaciaDAO;
    }
    
    @Override
    public Long cadastrarFarmacia(Farmacia farmacia) throws PersistenciaException, LogicaNegocioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizarFarmacia(Farmacia farmacia) throws PersistenciaException, LogicaNegocioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletarFarmacia(Long farmaciaId) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Farmacia getFarmaciaById(Long farmaciaId) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Farmacia> listAll() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
