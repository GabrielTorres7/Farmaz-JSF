/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.serviceImpl;

import br.cefetmg.farmaz.model.daoImpl.EnderecoDAOImpl;
import br.cefetmg.farmaz.model.dominio.Endereco;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterEndereco;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class ManterEnderecoImpl implements ManterEndereco{
    
    private final EnderecoDAOImpl enderecoDAO;

    public ManterEnderecoImpl(EnderecoDAOImpl enderecoDAO) {
        this.enderecoDAO = enderecoDAO;
    }

    @Override
    public Long inserirEndereco(Endereco endereco) throws PersistenciaException, LogicaNegocioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizarEndereco(Endereco endereco) throws PersistenciaException, LogicaNegocioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletarEndereco(Long enderecoId) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Endereco getEnderecoById(Long enderecoId) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Endereco> getEnderecosByClienteId(Long clienteId) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
