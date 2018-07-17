/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.dao;

import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface DisponibilidadeDAO {
    public Long insert(Disponibilidade disponibilidade) throws PersistenciaException;
    public boolean update(Disponibilidade disponibilidade) throws PersistenciaException;
    public boolean remove(Long produtoId, Long farmaciaId) throws PersistenciaException;
    public Disponibilidade getDisponibilidadeById(Long produtoId, Long farmaciaId) throws PersistenciaException;
    public List<Disponibilidade> getDisponibilidadeByProdutoId(Long produtoId) throws PersistenciaException;
    public List<Disponibilidade> getDisponibilidadeByFarmaciaId(Long farmaciaId) throws PersistenciaException;
}
